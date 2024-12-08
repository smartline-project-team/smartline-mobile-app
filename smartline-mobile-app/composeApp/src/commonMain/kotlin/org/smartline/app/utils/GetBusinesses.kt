package org.smartline.app.utils

import org.smartline.app.models.auth.ApiRequest
import org.smartline.app.models.buisnesses.Business
import org.smartline.app.serializers.ParseJson

suspend fun GetBusinesses(type: String): List<Business>? {
    var result: List<Business>? = null
    val apiRequest =
        ApiRequest(
            "https://smartlineapi.pythonanywhere.com/api/businesses/",
            mapOf()
        )

        val apiResponse = apiRequest.sendGet()
        if (apiResponse.status == 200) {
            result = ParseJson<List<Business>>(apiResponse.data)
        }

    return result
}