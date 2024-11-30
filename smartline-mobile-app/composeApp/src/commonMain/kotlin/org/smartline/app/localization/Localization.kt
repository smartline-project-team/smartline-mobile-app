package org.smartline.app.localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import org.smartline.app.models.Language

val LocalLocalization = staticCompositionLocalOf { Language.Russian.isoFormat }

@Composable
fun LocalizedApp(language: String = Language.Russian.isoFormat, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalLocalization provides language,
        content = content
    )
}