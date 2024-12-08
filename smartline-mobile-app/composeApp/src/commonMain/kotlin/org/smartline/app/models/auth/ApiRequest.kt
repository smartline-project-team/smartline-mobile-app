package org.smartline.app.models.auth

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

    suspend fun sendPost(): ApiResponse = withContext(Dispatchers.Default) {
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

    suspend fun sendGet(): ApiResponse = withContext(Dispatchers.Default) {
        val client = HttpClient()

        try {
            val response: HttpResponse = client.get(apiLink)

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
                error = e.message?: "Unknown error occurred"
            )
        } finally {
            client.close()
        }
    }
}


