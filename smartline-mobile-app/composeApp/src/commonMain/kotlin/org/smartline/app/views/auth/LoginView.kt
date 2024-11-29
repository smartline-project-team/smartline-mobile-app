package org.smartline.app.views.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import org.smartline.app.models.ApiRequest

@Composable
fun LoginView(showLogin: MutableState<Boolean>, showRegister: MutableState<Boolean>,
              showContent: MutableState<Boolean>) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    AnimatedVisibility(showLogin.value) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(value = email, onValueChange = {
                email = it
            }, label = { Text("E-mail") })

            TextField(value = password, onValueChange = {
                password = it.hashCode().toString()
            }, label = { Text("Password") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        val icon = if (isPasswordVisible) Icons.Filled.Add else Icons.Filled.Done
                        val description = if (isPasswordVisible) "Hide password" else "Show password"
                        Icon(imageVector = icon, contentDescription = description)
                    }
                }
            )

            Button(onClick = {
                val apiRequest =
                    ApiRequest(
                        "https://smartlineapi.pythonanywhere.com/api/auth/login/",
                        mapOf("email" to email, "password" to password))
                kotlinx.coroutines.MainScope().launch {
                    val apiResponse = apiRequest.send()
                    if (apiResponse.status == 200) {
                        showContent.value = true
                    }
                }
            }) {
                Text("Login")
            }
            Text("- or -")
            Button(onClick = {
                showRegister.value = true
                showLogin.value = false
            }) {
                Text("Sign up")
            }

        }
    }
}