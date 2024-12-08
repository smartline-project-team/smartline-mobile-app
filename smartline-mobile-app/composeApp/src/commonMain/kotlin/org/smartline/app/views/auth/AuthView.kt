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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.smartline.app.KColor
import org.smartline.app.generated.resources.Jost
import org.smartline.app.generated.resources.Res
import org.smartline.app.models.auth.ApiRequest
import org.smartline.app.models.resources.Resources

@Composable
fun TabRow(isEmailTabSelected: Boolean, onTabSelected: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = KColor.secondary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabButton(
            text = Resources.strings.numberText,
            isSelected = !isEmailTabSelected,
            modifier = Modifier.weight(1f) // Half width
        ) {
            onTabSelected(false)
        }
        TabButton(
            text = Resources.strings.emailText,
            isSelected = isEmailTabSelected,
            modifier = Modifier.weight(1f),
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
        Text(text = text, color = textColor, fontSize = 14.sp,
            fontFamily = FontFamily(Font(Res.font.Jost)))
    }
}

@Composable
fun AuthView(showContent: MutableState<Boolean>, showConfirmation: MutableState<Boolean>,
             email: MutableState<String>) {
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
                text = Resources.strings.loginText,
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily(Font(Res.font.Jost)),
                color = KColor.secondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            TabRow(isEmailTabSelected) { selected ->
                isEmailTabSelected = selected
            }

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = email.value,
                onValueChange = { email.value = it },
                textStyle = TextStyle(color = KColor.background, fontSize = 16.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                cursorBrush = SolidColor(KColor.background),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = KColor.secondary,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (email.value.isEmpty()) {
                            Text(
                                text = if(isEmailTabSelected) Resources.strings.enterEmailText
                                else Resources.strings.enterNumberText,
                                style = TextStyle(color = KColor.background, fontSize = 16.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    val apiRequest =
                        ApiRequest(
                            "https://smartlineapi.pythonanywhere.com/api/auth/send-code/",
                            mapOf("email" to email.value, "phone" to "string")
                        )
                    kotlinx.coroutines.MainScope().launch {
                        val apiResponse = apiRequest.sendPost()
                        if (apiResponse.status == 200) {
                            showConfirmation.value = true
                            showContent.value = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(KColor.primary)
            ) {
                Text(
                    text = Resources.strings.welcomeButtonText,
                    color = KColor.background,
                    style = TextStyle(fontSize = 16.sp),
                    fontFamily = FontFamily(Font(Res.font.Jost))
                )
            }
        }
    }
}