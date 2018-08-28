package jp.nephy.penicillin.models

import com.google.gson.JsonObject
import jp.nephy.jsonkt.byJsonObject


class LivePipelineSubscription(override val json: JsonObject): PenicillinModel {
    val subscriptions by json.byJsonObject
}
