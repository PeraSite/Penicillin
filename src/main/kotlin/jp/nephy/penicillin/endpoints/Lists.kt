@file:Suppress("UNUSED")

package jp.nephy.penicillin.endpoints

import jp.nephy.penicillin.PenicillinClient
import jp.nephy.penicillin.endpoints.parameters.ListCreationMode
import jp.nephy.penicillin.models.*
import jp.nephy.penicillin.models.Status
import jp.nephy.penicillin.models.User

class Lists(override val client: PenicillinClient): Endpoint {
    fun show(listId: Long, vararg options: Pair<String, Any?>)  = client.session.get("/1.1/lists/show.json") {
        parameter("list_id" to listId,  *options)
    }.jsonObject<TwitterList>()

    fun show(slug: String, ownerScreenName: String, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/show.json") {
        parameter("slug" to slug, "owner_screen_name" to ownerScreenName, *options)
    }.jsonObject<TwitterList>()

    fun show(slug: String, ownerId: Long, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/show.json") {
        parameter("slug" to slug, "owner_id" to ownerId, *options)
    }.jsonObject<TwitterList>()

    fun list(reverse: Boolean? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/list.json") {
        parameter("reverse" to reverse, *options)
    }.jsonArray<TwitterList>()

    fun list(userId: Long, reverse: Boolean? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/list.json") {
        parameter("user_id" to userId, "reverse" to reverse, *options)
    }.jsonArray<TwitterList>()

    fun list(screenName: String, reverse: Boolean? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/list.json") {
        parameter("screen_name" to screenName, "reverse" to reverse, *options)
    }.jsonArray<TwitterList>()

    fun timeline(
        listId: Long,
        sinceId: Long? = null,
        maxId: Long? = null,
        count: Int? = null,
        includeEntities: Boolean? = null,
        includeRTs: Boolean? = null,
        tweetMode: String? = null,
        includeMyRetweet: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/statuses.json") {
        parameter(
            "list_id" to listId,
            "since_id" to sinceId,
            "max_id" to maxId,
            "count" to count,
            "include_entities" to includeEntities,
            "include_rts" to includeRTs,
            "tweet_mode" to tweetMode,
            "include_my_retweet" to includeMyRetweet,
            *options
        )
    }.jsonArray<Status>()

    fun timeline(
            slug: String,
            ownerScreenName: String,
            sinceId: Long? = null,
            maxId: Long? = null,
            count: Int? = null,
            includeEntities: Boolean? = null,
            includeRTs: Boolean? = null,
            tweetMode: String? = null,
            includeMyRetweet: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/statuses.json") {
        parameter(
                "slug" to slug,
                "owner_screen_name" to ownerScreenName,
                "since_id" to sinceId,
                "max_id" to maxId,
                "count" to count,
                "include_entities" to includeEntities,
                "include_rts" to includeRTs,
                "tweet_mode" to tweetMode,
                "include_my_retweet" to includeMyRetweet,
                *options
        )
    }.jsonArray<Status>()

    fun timeline(
            slug: String,
            ownerId: Long,
            sinceId: Long? = null,
            maxId: Long? = null,
            count: Int? = null,
            includeEntities: Boolean? = null,
            includeRTs: Boolean? = null,
            tweetMode: String? = null,
            includeMyRetweet: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/statuses.json") {
        parameter(
                "slug" to slug,
                "owner_id" to ownerId,
                "since_id" to sinceId,
                "max_id" to maxId,
                "count" to count,
                "include_entities" to includeEntities,
                "include_rts" to includeRTs,
                "tweet_mode" to tweetMode,
                "include_my_retweet" to includeMyRetweet,
                *options
        )
    }.jsonArray<Status>()

    fun members(
        listId: Long,
        count: Int? = null,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members.json") {
        parameter(
            "list_id" to listId,
            "count" to count,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.cursorJsonObject<CursorUsers>()

    fun members(
            slug: String,
            ownerScreenName: String,
            count: Int? = null,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members.json") {
        parameter(
                "slug" to slug,
                "owner_screen_name" to ownerScreenName,
                "count" to count,
                "include_entities" to includeEntities,
                "skip_status" to skipStatus,
                *options
        )
    }.cursorJsonObject<CursorUsers>()

    fun members(
            slug: String,
            ownerId: Long,
            count: Int? = null,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members.json") {
        parameter(
                "slug" to slug,
                "owner_id" to ownerId,
                "count" to count,
                "include_entities" to includeEntities,
                "skip_status" to skipStatus,
                *options
        )
    }.cursorJsonObject<CursorUsers>()

    fun subscribers(
        listId: Long,
        count: Int? = null,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers.json") {
        parameter(
            "list_id" to listId,
            "count" to count,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.cursorJsonObject<CursorUsers>()

    fun subscribers(
            slug: String,
            ownerScreenName: String,
            count: Int? = null,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers.json") {
        parameter(
                "slug" to slug,
                "owner_screen_name" to ownerScreenName,
                "count" to count,
                "include_entities" to includeEntities,
                "skip_status" to skipStatus,
                *options
        )
    }.cursorJsonObject<CursorUsers>()

    fun subscribers(
            slug: String,
            ownerId: Long,
            count: Int? = null,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers.json") {
        parameter(
                "slug" to slug,
                "owner_id" to ownerId,
                "count" to count,
                "include_entities" to includeEntities,
                "skip_status" to skipStatus,
                *options
        )
    }.cursorJsonObject<CursorUsers>()

    fun ownerships(count: Int? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/ownerships.json") {
        parameter("count" to count, *options)
    }.cursorJsonObject<CursorLists>()

    fun ownerships(userId: Long, count: Int? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/ownerships.json") {
        parameter("user_id" to userId, "count" to count, *options)
    }.cursorJsonObject<CursorLists>()

    fun ownerships(screenName: String, count: Int? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/ownerships.json") {
        parameter("screen_name" to screenName, "count" to count, *options)
    }.cursorJsonObject<CursorLists>()

    fun memberships(count: Int? = null, filterToOwnedLists: Boolean? = null, vararg options: Pair<String, Any?>) =
    client.session.get("/1.1/lists/memberships.json") {
        parameter("count" to count, "filter_to_owned_lists" to filterToOwnedLists, *options)
    }.cursorJsonObject<CursorLists>()

    fun memberships(userId: Long, count: Int? = null, filterToOwnedLists: Boolean? = null, vararg options: Pair<String, Any?>) =
    client.session.get("/1.1/lists/memberships.json") {
        parameter("user_id" to userId, "count" to count, "filter_to_owned_lists" to filterToOwnedLists, *options)
    }.cursorJsonObject<CursorLists>()

    fun memberships(screenName: String, count: Int? = null, filterToOwnedLists: Boolean? = null, vararg options: Pair<String, Any?>) =
    client.session.get("/1.1/lists/memberships.json") {
        parameter("screen_name" to screenName, "count" to count, "filter_to_owned_lists" to filterToOwnedLists, *options)
    }.cursorJsonObject<CursorLists>()

    fun subscriptions(count: Int? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/subscriptions.json") {
        parameter("count" to count, *options)
    }.cursorJsonObject<CursorLists>()

    fun subscriptions(userId: Long, count: Int? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/subscriptions.json") {
        parameter("user_id" to userId, "count" to count, *options)
    }.cursorJsonObject<CursorLists>()

    fun subscriptions(screenName: String, count: Int? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/lists/subscriptions.json") {
        parameter("screen_name" to screenName, "count" to count, *options)
    }.cursorJsonObject<CursorLists>()

    fun member(listId: Long, userId: Long, includeEntities: Boolean? = null, skipStatus: Boolean? = null, vararg options: Pair<String, Any?>) =
    client.session.get("/1.1/lists/members/show.json") {
        parameter(
            "list_id" to listId,
            "user_id" to userId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun member(
            listId: Long,
            screenName: String,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members/show.json") {
        parameter(
            "list_id" to listId,
            "screen_name" to screenName,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun member(
            slug: String,
            userId: Long,
            ownerScreenName: String,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members/show.json") {
        parameter(
            "slug" to slug,
            "user_id" to userId,
            "owner_screen_name" to ownerScreenName,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun member(
            slug: String,
            userId: Long,
            ownerId: Long,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members/show.json") {
        parameter(
            "slug" to slug,
            "user_id" to userId,
            "owner_id" to ownerId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun member(
            slug: String,
            screenName: String,
            ownerScreenName: String,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members/show.json") {
        parameter(
            "slug" to slug,
            "screen_name" to screenName,
            "owner_screen_name" to ownerScreenName,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun member(
            slug: String,
            screenName: String,
            ownerId: Long,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/members/show.json") {
        parameter(
            "slug" to slug,
            "screen_name" to screenName,
            "owner_id" to ownerId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun subscriber(listId: Long, userId: Long, includeEntities: Boolean? = null, skipStatus: Boolean? = null, vararg options: Pair<String, Any?>) =
    client.session.get("/1.1/lists/subscribers/show.json") {
        parameter(
            "list_id" to listId,
            "user_id" to userId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun subscriber(
        listId: Long,
        screenName: String,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers/show.json") {
        parameter(
            "list_id" to listId,
            "screen_name" to screenName,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun subscriber(
        slug: String,
        userId: Long,
        ownerScreenName: String,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers/show.json") {
        parameter(
            "slug" to slug,
            "user_id" to userId,
            "owner_screen_name" to ownerScreenName,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun subscriber(
            slug: String,
            userId: Long,
            ownerId: Long,
            includeEntities: Boolean? = null,
            skipStatus: Boolean? = null,
            vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers/show.json") {
        parameter(
                "slug" to slug,
                "user_id" to userId,
                "owner_id" to ownerId,
                "include_entities" to includeEntities,
                "skip_status" to skipStatus,
                *options
        )
    }.jsonObject<User>()

    fun subscriber(
        slug: String,
        screenName: String,
        ownerScreenName: String,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers/show.json") {
        parameter(
            "slug" to slug,
            "screen_name" to screenName,
            "owner_screen_name" to ownerScreenName,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun subscriber(
        slug: String,
        screenName: String,
        ownerId: Long,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.get("/1.1/lists/subscribers/show.json") {
        parameter(
            "slug" to slug,
            "screen_name" to screenName,
            "owner_id" to ownerId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }.jsonObject<User>()

    fun create(name: String, mode: ListCreationMode? = null, description: String? = null, vararg options: Pair<String, Any?>) =
    client.session.post("/1.1/lists/create.json") {
        body {
            form {
                add("name" to name, "mode" to mode?.value, "description" to description, *options)
            }
        }
    }.jsonObject<TwitterList>()

    fun destroy(listId: Long, vararg options: Pair<String, Any?>) =
    client.session.post("/1.1/lists/destroy.json") {
        body {
            form {
                add("list_id" to listId, *options)
            }
        }
    }.jsonObject<TwitterList>()

    fun destroy(ownerScreenName: String, slug: String? = null, vararg options: Pair<String, Any?>) =
    client.session.post("/1.1/lists/destroy.json") {
        body {
            form {
                add("owner_screen_name" to ownerScreenName, "slug" to slug, *options)
            }
        }
    }.jsonObject<TwitterList>()

    fun destroy(ownerId: Long, slug: String? = null, vararg options: Pair<String, Any?>) =
    client.session.post("/1.1/lists/destroy.json") {
        body {
            form {
                add("owner_id" to ownerId, "slug" to slug, *options)
            }
        }
    }.jsonObject<TwitterList>()

    fun update(
        listId: Long? = null,
        name: String? = null,
        mode: ListCreationMode? = null,
        description: String? = null,
        vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/update.json") {
        body {
            form {
                add("list_id" to listId, "name" to name, "mode" to mode?.value, "description" to description, *options)
            }
        }
    }.empty()

    fun update(
        slug: String,
        name: String? = null,
        mode: ListCreationMode? = null,
        description: String? = null,
        ownerScreenName: String,
        vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/update.json") {
        body {
            form {
                add("slug" to slug, "name" to name, "mode" to mode?.value, "description" to description, "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun update(
        slug: String,
        name: String? = null,
        mode: ListCreationMode? = null,
        description: String? = null,
        ownerId: Long,
        vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/update.json") {
        body {
            form {
                add("slug" to slug, "name" to name, "mode" to mode?.value, "description" to description, "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun addMember(listId: Long, userId: Long, vararg options: Pair<String, Any?>) =
    client.session.post("/1.1/lists/members/create.json") {
        body {
            form {
                add("list_id" to listId, "user_id" to userId, *options)
            }
        }
    }.empty()

    fun addMember(listId: Long, screenName: String, vararg options: Pair<String, Any?>) =
    client.session.post("/1.1/lists/members/create.json") {
        body {
            form {
                add("list_id" to listId, "screen_name" to screenName, *options)
            }
        }
    }.empty()

    fun addMember(
        slug: String, userId: Long, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userId, "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun addMember(
        slug: String, userId: Long, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userId, "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun addMember(
        slug: String, screenName: String, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenName, "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun addMember(
        slug: String, screenName: String, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenName, "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun addMembersByIds(listId: Long, userIds: List<Long>, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/members/create_all.json") {
            body {
                form {
                    add("list_id" to listId, "user_id" to userIds.joinToString(","), *options)
                }
            }
        }.empty()

    fun addMembersByScreenName(listId: Long, screenNames: List<String>, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/members/create_all.json") {
            body {
                form {
                    add("list_id" to listId, "screen_name" to screenNames.joinToString(","), *options)
                }
            }
        }.empty()

    fun addMembersByIds(
            slug: String, userIds: List<Long>, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create_all.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userIds.joinToString(","), "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun addMembersByIds(
            slug: String, userIds: List<Long>, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create_all.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userIds.joinToString(","), "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun addMembersByScreenName(
            slug: String, screenNames: List<String>, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create_all.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenNames.joinToString(","), "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun addMembersByScreenName(
            slug: String, screenNames: List<String>, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/create_all.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenNames.joinToString(","), "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun removeMember(listId: Long, userId: Long, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/members/destroy.json") {
            body {
                form {
                    add("list_id" to listId, "user_id" to userId, *options)
                }
            }
        }.empty()

    fun removeMember(listId: Long, screenName: String, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/members/destroy.json") {
            body {
                form {
                    add("list_id" to listId, "screen_name" to screenName, *options)
                }
            }
        }.empty()

    fun removeMember(
            slug: String, userId: Long, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userId, "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun removeMember(
            slug: String, userId: Long, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userId, "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun removeMember(
            slug: String, screenName: String, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenName, "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun removeMember(
            slug: String, screenName: String, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenName, "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun removeMembersByIds(listId: Long, userIds: List<Long>, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/members/destroy_all.json") {
            body {
                form {
                    add("list_id" to listId, "user_id" to userIds.joinToString(","), *options)
                }
            }
        }.empty()

    fun removeMembersByScreenName(listId: Long, screenNames: List<String>, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/members/destroy_all.json") {
            body {
                form {
                    add("list_id" to listId, "screen_name" to screenNames.joinToString(","), *options)
                }
            }
        }.empty()

    fun removeMembersByIds(
            slug: String, userIds: List<Long>, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy_all.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userIds.joinToString(","), "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun removeMembersByIds(
            slug: String, userIds: List<Long>, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy_all.json") {
        body {
            form {
                add("slug" to slug, "user_id" to userIds.joinToString(","), "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun removeMembersByScreenName(
            slug: String, screenNames: List<String>, ownerScreenName: String, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy_all.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenNames.joinToString(","), "owner_screen_name" to ownerScreenName, *options)
            }
        }
    }.empty()

    fun removeMembersByScreenName(
            slug: String, screenNames: List<String>, ownerId: Long, vararg options: Pair<String, Any?>
    ) = client.session.post("/1.1/lists/members/destroy_all.json") {
        body {
            form {
                add("slug" to slug, "screen_name" to screenNames.joinToString(","), "owner_id" to ownerId, *options)
            }
        }
    }.empty()

    fun subscribe(listId: Long, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/subscribers/create.json") {
            body {
                form {
                    add("list_id" to listId, *options)
                }
            }
        }.jsonObject<TwitterList>()

    fun subscribe(ownerScreenName: String, slug: String, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/subscribers/create.json") {
            body {
                form {
                    add("owner_screen_name" to ownerScreenName, "slug" to slug, *options)
                }
            }
        }.jsonObject<TwitterList>()

    fun subscribe(ownerId: Long, slug: String, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/subscribers/create.json") {
            body {
                form {
                    add("owner_id" to ownerId, "slug" to slug, *options)
                }
            }
        }.jsonObject<TwitterList>()

    fun unsubscribe(listId: Long, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/subscribers/destroy.json") {
            body {
                form {
                    add("list_id" to listId, *options)
                }
            }
        }.empty()

    fun unsubscribe(slug: String, ownerScreenName: String, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/subscribers/destroy.json") {
            body {
                form {
                    add("slug" to slug, "owner_screen_name" to ownerScreenName, *options)
                }
            }
        }.empty()

    fun unsubscribe(slug: String, ownerId: Long, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/lists/subscribers/destroy.json") {
            body {
                form {
                    add("slug" to slug, "owner_id" to ownerId, *options)
                }
            }
        }.empty()
}
