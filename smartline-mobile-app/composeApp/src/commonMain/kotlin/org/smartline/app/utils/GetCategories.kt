package org.smartline.app.utils

import org.smartline.app.models.auth.ApiRequest
import org.smartline.app.models.buisnesses.Business
import org.smartline.app.models.buisnesses.Category
import org.smartline.app.serializers.ParseJson

suspend fun GetCategories(): List<Category>? {
    var result: List<Category>? = null
    val apiRequest =
        ApiRequest(
            "https://smartlineapi.pythonanywhere.com/api/businesses/categories/",
            mapOf()
        )

    val apiResponse = apiRequest.sendGet()
    if (apiResponse.status == 200) {
        result = ParseJson<List<Category>>(apiResponse.data)
    }

    return result
}