package org.smartline.app.models.auth

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ConfirmationCode (
    val message: String,
    val tokens: Map<String, String>
)