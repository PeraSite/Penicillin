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

@file:Suppress("UNUSED", "PublicApiImplicitType", "KDocMissingDocumentation")

package blue.starry.penicillin.models.entities

import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.delegation.*
import blue.starry.penicillin.core.session.ApiClient


import blue.starry.penicillin.models.FaceCoordinate
import blue.starry.penicillin.models.PenicillinModel
import blue.starry.penicillin.models.Photo
import blue.starry.penicillin.models.UrlEntityModel

data class MediaEntity(override val json: JsonObject, override val client: ApiClient): UrlEntityModel {
    val additionalMediaInfo by nullableModel("additional_media_info") { AdditionalMediaInfo(it, client) }
    override val displayUrl by string("display_url")
    override val expandedUrl by string("expanded_url")
    val extAltText by nullableString("ext_alt_text")
    val features by nullableModel { Feature(it, client) }
    val id by long
    val idStr by string("id_str")
    override val indices by intList
    val mediaUrl by string("media_url")
    val mediaUrlHttps by string("media_url_https")
    val sizes by nullableModel { Photo(it, client) }
    val sourceStatusId by nullableLong("source_status_id")
    val sourceStatusIdStr by nullableString("source_status_id_str")
    val type by string
    override val url by string
    val videoInfo by nullableModel("video_info") { VideoInfo(it, client) }

    data class AdditionalMediaInfo(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val title by string
        val description by string
        val embeddable by boolean
    }

    data class Feature(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val large by model { Size(it, client) }
        val medium by model { Size(it, client) }
        val orig by model { Size(it, client) }
        val small by model { Size(it, client) }

        data class Size(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
            val faces by modelList { FaceCoordinate(it, client) }
        }
    }

    data class VideoInfo(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val durationMillis by int("duration_millis")
        val aspectRatio by intList
        val variants by modelList { Variant(it, client) }

        data class Variant(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
            val bitrate by nullableInt
            val contentType by string("content_type")
            val url by string
        }
    }
}
