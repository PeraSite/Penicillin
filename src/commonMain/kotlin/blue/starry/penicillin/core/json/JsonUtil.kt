package blue.starry.penicillin.core.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.encodeToJsonElement

@OptIn(ExperimentalSerializationApi::class)
public val json: Json = Json {
    encodeDefaults = true
    explicitNulls = false
}

public inline fun <reified T : Any> JsonObjectBuilder.putObject(key: String, obj: T): JsonElement? = put(key, json.encodeToJsonElement(obj))
