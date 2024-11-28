package org.smartline.app.models

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApiRequest(val apiLink: String, val parameters: Map<String, String>) {

    suspend fun send(): ApiResponse = withContext(Dispatchers.Default) {
        val client = HttpClient()
        
        try {
            val jsonBody = Json.encodeToString(parameters)
            val response: HttpResponse = client.post(apiLink) {
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }

            val responseBody: String = response.body()
            
            ApiResponse(
                status = response.status.value,
                data = responseBody,
                error = null
            )
        } catch (e: Exception) {
            ApiResponse(
                status = 500,
                data = null,
                error = e.message ?: "Unknown error occurred"
            )
        } finally {
            client.close()
        }
    }
}

data class ApiResponse(
    val status: Int,
    val data: Any? = null,
    val error: String? = null
)

