package org.smartline.app.models.auth

import kotlinx.serialization.json.Json

data class ApiResponse(
    val status: Int,
    val data: Any? = null,
    val error: String? = null)

