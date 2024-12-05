package org.smartline.app.models.buisnesses

import kotlinx.serialization.Serializable

@Serializable
data class Business(
    val id: Int,
    val name: String,
    val description: String,
    val categories: List<Category>)
