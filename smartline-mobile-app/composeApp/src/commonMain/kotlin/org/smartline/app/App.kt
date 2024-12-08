package org.smartline.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.russhwolf.settings.Settings
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.smartline.app.models.resources.LocalStringResources
import org.smartline.app.models.resources.StringFactory
import org.smartline.app.models.resources.StringResources
import org.smartline.app.views.auth.AuthView
import org.smartline.app.views.auth.ConfirmEmailView
import org.smartline.app.views.main.CategoriesScreenView
import org.smartline.app.views.main.BusinessesScreenView
import org.smartline.app.views.start.WelcomeView


@Composable
@Preview
fun App() {
    val currentLanguage = remember { mutableStateOf(language) } // Default language
    val stringResources: StringResources = StringFactory.createStrings(currentLanguage.value)
    CompositionLocalProvider(
        LocalStringResources provides stringResources
    ) {
        val settings = Settings()
        val screen = remember { mutableStateOf("welcomeScreen") }
        if (settings.getStringOrNull("token") != null) screen.value = "mainAppScreen"
        MaterialTheme {
            when (screen.value) {
                "welcomeScreen" -> {
                    WelcomeView(screen, currentLanguage)
                }

                "authScreen" -> {
                    val showContent = remember { mutableStateOf(true) }
                    val showConfirmation = remember { mutableStateOf(false) }
                    val showNext = remember { mutableStateOf(false) }
                    val email = remember { mutableStateOf("") }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AuthView(showContent = showContent,
                                showConfirmation = showConfirmation, email)
                            ConfirmEmailView(showContent = showConfirmation, email, showNext)
                            if (showNext.value) {
                                screen.value = "mainAppScreen"
                            }
                        }
                    }
                }

                "mainAppScreen" -> CategoriesScreenView(screen)
                "businessesScreen" -> BusinessesScreenView("")
            }
        }
    }
}