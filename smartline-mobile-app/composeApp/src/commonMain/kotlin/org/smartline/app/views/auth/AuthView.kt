package org.smartline.app.views.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.smartline.app.KColor
import org.smartline.app.models.ApiRequest

@Composable
fun TabRow(isEmailTabSelected: Boolean, onTabSelected: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabButton(
            text = "Номер",
            isSelected = !isEmailTabSelected,
            modifier = Modifier.weight(1f) // Half width
        ) {
            onTabSelected(false)
        }
        TabButton(
            text = "Почта",
            isSelected = isEmailTabSelected,
            modifier = Modifier.weight(1f) // Half width
        ) {
            onTabSelected(true)
        }
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) KColor.primary else Color.Transparent
    val textColor = KColor.background

    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, fontSize = 14.sp)
    }
}

@Composable
fun AuthView(showContent: MutableState<Boolean>, showConfirmation: MutableState<Boolean>) {
    var email by remember { mutableStateOf("") }
    var isEmailTabSelected by remember { mutableStateOf(true) }

    AnimatedVisibility(visible = showContent.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Вход",
                style = MaterialTheme.typography.h3,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            TabRow(isEmailTabSelected) { selected ->
                isEmailTabSelected = selected
            }

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = email,
                onValueChange = { email = it },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (email.isEmpty()) {
                            Text(
                                text = "Введите ваш номер",
                                style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "У вас уже есть аккаунт?",
                style = TextStyle(color = Color.Gray, fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val apiRequest =
                        ApiRequest(
                            "https://smartlineapi.pythonanywhere.com/api/auth/login/",
                            mapOf("email" to email, "password" to "pidorasik1")
                        )
                    kotlinx.coroutines.MainScope().launch {
                        val apiResponse = apiRequest.send()
                        if (apiResponse.status == 200) {
                            showConfirmation.value = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(
                    text = "Далее",
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}