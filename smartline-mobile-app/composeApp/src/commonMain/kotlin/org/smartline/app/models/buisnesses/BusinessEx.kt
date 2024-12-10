package org.smartline.app.models.buisnesses

import kotlinx.serialization.Serializable

@Serializable
data class BusinessEx (
    val id: Int,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val categories: List<Category>,
    val created_at: String,
    val updated_at: String,
    val specialists: List<Specialist>
)