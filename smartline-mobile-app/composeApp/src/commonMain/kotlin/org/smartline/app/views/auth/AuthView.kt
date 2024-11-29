package org.smartline.app.views.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.smartline.app.models.ApiRequest

@Composable
fun AuthView(showContent: MutableState<Boolean>, showConfirmation: MutableState<Boolean>) {
    var email by remember { mutableStateOf("") }
    AnimatedVisibility(showContent.value) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") }
            )
            Button(onClick = {
                val apiRequest =
                    ApiRequest(
                        "https://smartlineapi.pythonanywhere.com/api/auth/login/",
                        mapOf("email" to email, "password" to "pidorasik1"))
                kotlinx.coroutines.MainScope().launch {
                    val apiResponse = apiRequest.send()
                    if (apiResponse.status == 200) {
                        showConfirmation.value = true
                    }
                }
            }) {
                Text("Login")
            }
        }
    }
}