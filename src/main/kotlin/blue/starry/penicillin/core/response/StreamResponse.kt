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

package blue.starry.penicillin.core.response

import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import blue.starry.penicillin.core.request.action.ApiAction
import blue.starry.penicillin.core.session.ApiClient
import blue.starry.penicillin.core.streaming.handler.StreamHandler
import blue.starry.penicillin.core.streaming.listener.StreamListener

/**
 * The [ApiResponse] that provides stream-able response.
 */
data class StreamResponse<L: StreamListener, H: StreamHandler<L>>(
    override val client: ApiClient,
    override val request: HttpRequest,
    override val response: HttpResponse,
    override val action: ApiAction<StreamResponse<L, H>>
): ApiResponse<StreamResponse<L, H>>
