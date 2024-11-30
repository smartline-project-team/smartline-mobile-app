package org.smartline.app.models

data class ApiResponse(
    val status: Int,
    val data: Any? = null,
    val error: String? = null
)
