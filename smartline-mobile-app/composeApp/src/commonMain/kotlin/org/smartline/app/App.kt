package org.smartline.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.smartline.app.models.Language
import org.smartline.app.views.auth.AuthView
import org.smartline.app.views.auth.ConfirmEmailView
import org.smartline.app.views.start.WelcomeView
import org.smartline.app.localization.LocalizedApp
import org.smartline.app.localization.changeLang
import org.smartline.app.generated.resources.Res
import org.smartline.app.generated.resources.welcome_message


@Composable
@Preview
fun App() {
    val screen = remember { mutableStateOf("welcomeScreen") }
    MaterialTheme {
        when (screen.value) {
            "welcomeScreen" -> {
                WelcomeView(screen)
            }

            "authScreen" -> {
                val showContent = remember { mutableStateOf(true) }
                val showConfirmation = remember { mutableStateOf(false) }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AuthView(showContent = showContent, showConfirmation = showConfirmation)
                        ConfirmEmailView(showContent = showConfirmation)
                    }
                }
            }
        }
    }
    }

private fun switchLanguage(lang: String) : String{
    return when (lang) {
        Language.English.isoFormat -> Language.Russian.isoFormat
        Language.Russian.isoFormat -> Language.English.isoFormat
        else -> Language.English.isoFormat
    }
}