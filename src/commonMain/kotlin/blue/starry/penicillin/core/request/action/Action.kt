/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("UNUSED")

package blue.starry.penicillin.core.request.action

import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.JsonPrimitive
import blue.starry.jsonkt.delegation.byNullableInt
import blue.starry.jsonkt.delegation.byNullableString
import blue.starry.jsonkt.jsonArrayOrNull
import blue.starry.jsonkt.string
import blue.starry.penicillin.core.exceptions.PenicillinException
import blue.starry.penicillin.core.exceptions.throwApiError
import blue.starry.penicillin.core.i18n.LocalizedString
import blue.starry.penicillin.core.request.url
import blue.starry.penicillin.extensions.session
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.CancellationException
import mu.KotlinLogging

private val apiActionLogger = KotlinLogging.logger("Penicillin.ApiAction")

internal suspend fun ApiAction<*>.finalize(): Pair<HttpRequest, HttpResponse> {
    try {
        val response = session.httpClient.request(request.builder.finalize())

        return response.request to response
    } catch (e: CancellationException) {
        throw e
    } catch (t: Throwable) {
        throw PenicillinException(LocalizedString.ApiRequestFailed, t, null, null, request.builder.url)
    }
}

internal fun checkError(request: HttpRequest, response: HttpResponse, content: String? = null, json: JsonObject? = null) {
    apiActionLogger.trace{"${response.version} ${response.status.value} ${request.method.value} ${request.url}"}
    apiActionLogger.trace{"Request : $json"}
    apiActionLogger.trace {
        "Response : " + when {
            content == null -> {
                "(Streaming Response)"
            }
            content.isBlank() -> {
                "(Empty Response)"
            }
            else -> {
                content
            }
        }
    }

    if (response.status.isSuccess()) {
        return
    }

    if (json != null) {
        when (val error = json["errors"]?.jsonArrayOrNull?.firstOrNull() ?: json["error"]) {
            is JsonObject -> {
                val code by error.byNullableInt
                val message by error.byNullableString

                throwApiError(code, message.orEmpty(), content!!, request, response)
            }
            is JsonPrimitive -> {
                throwApiError(null, error.string, content!!, request, response)
            }
            else -> {
                throw PenicillinException(LocalizedString.UnknownApiErrorWithStatusCode, null, request, response, response.status.value, content)
            }
        }
    }

    throw PenicillinException(LocalizedString.ApiReturnedNon200StatusCode, null, request, response, response.status.value, response.status.description)
}

internal suspend inline fun HttpResponse.readTextOrNull(): String? {
    return runCatching {
        bodyAsText()
    }.onFailure {
        apiActionLogger.debug(it) { "Failed to read text." }
    }.getOrNull()
}
