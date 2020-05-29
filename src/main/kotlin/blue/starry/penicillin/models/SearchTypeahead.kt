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

package blue.starry.penicillin.models

import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.delegation.*
import blue.starry.penicillin.core.session.ApiClient


data class SearchTypeahead(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
    val completedIn by float("completed_in")
    val hashtags by stringList
    val numResults by int("num_results")
    val oneclick by stringList
    val query by string
    val topics by modelList { Topic(it, client) }
    val users by modelList { UserTypeahead(it, client) }

    data class Topic(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val inline by boolean
        val roundedScore by int("rounded_score")
        val tokens by modelList { SearchToken(it, client) }
        val topic by string
    }

    data class UserTypeahead(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val canMediaTag by boolean("can_media_tag")
        val connectingUserCount by int("connecting_user_count")
        val connectingUserIds by longList("connecting_user_ids")
        val id by long
        val idStr by string("id_str")
        val inline by boolean
        val isBlocked by boolean("is_blocked")
        val isDmAble by boolean("is_dm_able")
        val isProtected by boolean("is_protected")
        val location by nullableString
        val name by string
        val profileImageUrl by nullableString("profile_image_url")
        val profileImageUrlHttps by nullableString("profile_image_url_https")
        val roundedGraphWeight by int("rounded_graph_weight")
        val roundedScore by int("rounded_score")
        val screenName by string("screen_name")
        val socialContext by model("social_context") { SocialContext(it, client) }
        val socialProof by int("social_proof")
        val socialProofsOrdered by intList("social_proofs_ordered")
        val tokens by modelList { SearchToken(it, client) }
        val verified by boolean

        data class SocialContext(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
            val following by boolean
            val followedBy by boolean("followed_by")
        }
    }

    data class SearchToken(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val token by string
    }
}
