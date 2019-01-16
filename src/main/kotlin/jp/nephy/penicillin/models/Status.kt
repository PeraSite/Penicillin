@file:Suppress("UNUSED")

package jp.nephy.penicillin.models

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.delegation.*

data class Status(override val json: JsonObject): PenicillinModel {
    val contributors by modelList<Contributor>()
    val conversationId by nullableLong("conversation_id")
    val coordinates by nullableModel<Coordinate>()
    val currentUserRetweet by nullableModel<CurrentUserRetweet>(key = "current_user_retweet")
    // val createdAt by string("created_at")
    val displayTextRange by intList("display_text_range")
    val entities by model<StatusEntity>()
    val extendedEntities by nullableModel<ExtendedEntity>(key = "extended_entities")
    val extendedTweet by nullableModel<ExtendedTweet>(key = "extended_tweet")
    val favoriteCount by int("favorite_count")
    val favorited by boolean
    val filterLevel by nullableString("filter_level")
    val fullText by nullableString("full_text")
    @Deprecated("geo field is deprecated. Use coordinates instead.")
    val geo by nullableJsonObject
    val id by long
    val idStr by string("id_str")
    val inReplyToScreenName by nullableString("in_reply_to_screen_name")
    val inReplyToStatusId by nullableLong("in_reply_to_status_id")
    val inReplyToStatusIdStr by nullableString("in_reply_to_status_id_str")
    val inReplyToUserId by nullableLong("in_reply_to_user_id")
    val inReplyToUserIdStr by nullableString("in_reply_to_user_id_str")
    val isQuoteStatus by boolean("is_quote_status")
    // val lang by string
    val place by nullableModel<Place>()
    val possiblySensitive by nullableBoolean("possibly_sensitive")
    val possiblySensitiveEditable by nullableBoolean("possibly_sensitive_editable")
    val quotedStatus by nullableModel<Status>(key = "quoted_status")
    val quotedStatusId by nullableLong("quoted_status_id")
    val quotedStatusIdStr by nullableString("quoted_status_id_str")
    val quoteCount by nullableInt("quote_count")
    val replyCount by nullableInt("reply_count")
    val retweetCount by int("retweet_count")
    val retweeted by boolean
    val retweetedStatus by nullableModel<Status>(key = "retweeted_status")
    val source by string
    val supplementalLanguage by nullableString("supplemental_language") // null
    val text by string
    val timestampMs by nullableString("timestamp_ms")
    val truncated by boolean
    val user by model<User>()
    val withheldCopyright by nullableBoolean("withheld_copyright")
    val withheldInCountries by stringList("withheld_in_countries")
    val withheldScope by nullableString("withheld_scope")

    data class Contributor(override val json: JsonObject): PenicillinModel {
        val id by long
        val idStr by string("id_str")
        val screenName by string("screen_name")
    }

    data class Coordinate(override val json: JsonObject): PenicillinModel {
        private val coordinates by floatList
        val type by string
        val longitude by nullableLambda {
            if (coordinates.size == 2) {
                coordinates[0]
            } else {
                null
            }
        }
        val latitude by nullableLambda {
            if (coordinates.size == 2) {
                coordinates[1]
            } else {
                null
            }
        }
    }

    data class CurrentUserRetweet(override val json: JsonObject): PenicillinModel {
        val id by long
        val idStr by string("id_str")
    }

    data class ExtendedEntity(override val json: JsonObject): PenicillinModel {
        val displayTextRange by intList("display_text_range")
        val media by modelList<MediaEntity>()
        val fullText by nullableString("full_text")
    }

    data class ExtendedTweet(override val json: JsonObject): PenicillinModel {
        val displayTextRange by intList("display_text_range")
        val entities by model<StatusEntity>()
        val fullText by nullableString("full_text")
    }
}
