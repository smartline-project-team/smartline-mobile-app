package org.smartline.app.views.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.smartline.app.models.ApiRequest
import org.smartline.app.utils.ConvertCode

@Composable
fun ConfirmEmailView(showContent: MutableState<Boolean>, email: MutableState<String>,
                     showNext: MutableState<Boolean>) {
    val digitsQuantity = 6
    var confirmationCode  = "pidor"
    var code by remember { mutableStateOf(List(digitsQuantity) { "" }) }
    val focusRequesters = List(digitsQuantity) { FocusRequester() }
    AnimatedVisibility(showContent.value) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                code.forEachIndexed { index, digit ->
                    TextField(
                        value = digit,
                        onValueChange = { input ->
                            if (input.length <= 1 && input.all { char -> char.isDigit() }) {
                                val newCode = code.toMutableList()
                                newCode[index] = input
                                code = newCode

                                if (input.isNotEmpty() && index < digitsQuantity - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(45.dp)
                            .height(60.dp)
                            .focusRequester(focusRequesters[index]),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (index == digitsQuantity) {
                                    focusRequesters[index - 1].freeFocus()
                                }
                                confirmationCode = ConvertCode(code)
                                println(confirmationCode)
                                println(digitsQuantity)
                                println(email.value)
                                val apiRequest =
                                    ApiRequest(
                                        "https://smartlineapi.pythonanywhere.com/api/auth/confirm-code/",
                                        mapOf("email" to email.value, "phone" to "string",
                                            "code" to confirmationCode)
                                    )
                                kotlinx.coroutines.MainScope().launch {
                                    println("ok")
                                    val apiResponse = apiRequest.send()
                                    println(apiResponse)
                                    if (apiResponse.status == 200) {
                                        showNext.value = true
                                        showContent.value = false
                                    }
                                }
                            }
                        )
                    )
                }
            }
            Text(confirmationCode)
        }
    }
}