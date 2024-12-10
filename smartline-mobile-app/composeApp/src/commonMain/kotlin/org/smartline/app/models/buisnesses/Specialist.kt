package org.smartline.app.models.buisnesses

import kotlinx.serialization.Serializable

@Serializable
data class Specialist(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val photo: String,
    val business: Int
)
