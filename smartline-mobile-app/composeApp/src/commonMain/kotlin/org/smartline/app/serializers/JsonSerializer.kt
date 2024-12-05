package org.smartline.app.serializers

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

inline fun <reified T> ParseJson(data: Any?): T? {
    val jsonData = data as? String ?: return null

    return try {
        Json.decodeFromString<T>(jsonData)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}