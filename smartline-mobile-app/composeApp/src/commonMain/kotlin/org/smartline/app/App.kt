package org.smartline.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.smartline.app.views.LoginView
import org.smartline.app.views.RegisterView


@Composable
@Preview
fun App() {
    MaterialTheme {
        val showContent = remember { mutableStateOf(false) }
        val showLogin = remember { mutableStateOf(true) }
        val showRegister = remember { mutableStateOf(false) }
        Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoginView(showLogin = showLogin, showRegister = showRegister, showContent = showContent)
            RegisterView(showLogin = showLogin, showRegister = showRegister, showContent = showContent)
        }
    }
}