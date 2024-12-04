package org.smartline.app.models.auth

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ConfirmationCode (
    val message: String,
    val tokens: Map<String, String>
)


fun parseConfirmationCode(data: Any?): ConfirmationCode? {
    val jsonData = data as? String ?: return null

    return try {
        Json.decodeFromString<ConfirmationCode>(jsonData)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}