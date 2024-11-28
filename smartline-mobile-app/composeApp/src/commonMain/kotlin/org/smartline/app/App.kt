package org.smartline.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.smartline.app.models.ApiRequest
import org.smartline.app.models.ApiResponse


@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var showLogin by remember { mutableStateOf(true) }
        var showRegister by remember { mutableStateOf(false) }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var apiResponse by remember { mutableStateOf(ApiResponse(500, null, null)) }
        Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AnimatedVisibility(showLogin) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextField(value = email, onValueChange = {
                        email = it
                    }, label = { Text("E-mail") })

                    TextField(value = password, onValueChange = {
                        password = it
                    }, label = { Text("Password") })

                    Button(onClick = {
                        val apiRequest =
                            ApiRequest(
                                "https://smartlineapi.pythonanywhere.com/api/auth/login/",
                                mapOf("email" to email, "password" to password))
                        kotlinx.coroutines.MainScope().launch {
                            apiResponse = apiRequest.send()
                            showContent = true
                        }
                    }) {
                        Text("Login")
                    }
                    Text("- or -")
                    Button(onClick = {
                        showRegister = true
                        showLogin = false
                    }) {
                        Text("Sign up")
                    }
                    AnimatedVisibility(showContent) {
                        Column {
                            Text("Status: ${apiResponse.status}")
                            Text("Data: ${apiResponse.data}")
                            Text("Error: ${apiResponse.error}")
                        }
                    }
                }
            }
            AnimatedVisibility(showRegister) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    var repeatedPassword by remember { mutableStateOf("") }

                    TextField(value = email, onValueChange = {
                        email = it
                    }, label = { Text("E-mail") })

                    TextField(value = password, onValueChange = {
                        password = it
                    }, label = { Text("Password") })

                    TextField(value = repeatedPassword, onValueChange = {
                        repeatedPassword = it
                    }, label = { Text("Confirm password") })

                    Button(onClick = {
                        val apiRequest =
                            ApiRequest(
                                "https://smartlineapi.pythonanywhere.com/api/auth/register/",
                                mapOf("email" to email, "password1" to password,
                                "password2" to repeatedPassword))
                        kotlinx.coroutines.MainScope().launch {
                            apiResponse = apiRequest.send()
                            showRegister = false
                            showLogin = true
                            showContent = true
                        }
                    }) {
                        Text("Sign up")
                    }
                }
            }
        }
    }
}