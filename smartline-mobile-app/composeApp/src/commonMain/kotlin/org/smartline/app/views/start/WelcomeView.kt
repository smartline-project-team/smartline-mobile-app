package org.smartline.app.views.start

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font
import org.smartline.app.KColor
import org.smartline.app.generated.resources.Jost
import org.smartline.app.generated.resources.Res
import org.smartline.app.models.resources.Resources

@Composable
fun WelcomeView(next: MutableState<String>, currentLanguage: MutableState<String>) {
    val alphaText = remember { Animatable(0f) }
    val alphaButton = remember { Animatable(0f) }
    val welcomeMessage = Resources.strings.welcomeMessage
    val welcomeButtonText = Resources.strings.welcomeButtonText
    val languages = listOf(Resources.strings.englishText, Resources.strings.russianText,
        Resources.strings.kyrgyzText)
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(currentLanguage.value) }
    LaunchedEffect(Unit) {
        alphaText.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
        delay(500)
        alphaButton.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KColor.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = welcomeMessage,
                color = KColor.secondary,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(Res.font.Jost)),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alphaText.value)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { next.value = "authScreen" },
                modifier = Modifier
                    .alpha(alphaButton.value)
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(KColor.primary)
            ) {
                Text(text = welcomeButtonText, color = KColor.background,
                    fontSize = 16.sp, fontFamily = FontFamily(Font(Res.font.Jost)))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .alpha(alphaButton.value)
                .align(Alignment.TopEnd)
                .wrapContentSize(Alignment.TopEnd)
                .padding(8.dp)
                .background(KColor.background, shape = RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(8.dp)

        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    selectedLanguage
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White).alpha(alphaButton.value)
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        onClick = {
                            when (language) {
                                "English" -> currentLanguage.value = "en"
                                "Русский" -> currentLanguage.value = "ru"
                                "Кыргыздар" -> currentLanguage.value = "kg"
                                else -> currentLanguage.value = "en"
                            }
                            selectedLanguage = currentLanguage.value
                            expanded = false
                        }
                    ) {
                        Text(text = language)
                    }
                }
            }
        }
    }
}