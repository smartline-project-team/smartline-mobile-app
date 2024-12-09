package org.smartline.app.views.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import org.smartline.app.KColor
import org.smartline.app.generated.resources.Jost
import org.smartline.app.generated.resources.Res

@Composable
fun MainScreenView() {
    var isMenuOpen by remember { mutableStateOf(false) }
    val mainCategories = listOf("Финансы", "Развлечения", "Медицина", "Салон красоты")

        AnimatedVisibility(isMenuOpen) {
            MenuView()
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(KColor.background)
        ) {
            TopAppBar(
                title = { Text("Главная") },
                navigationIcon = {
                    IconButton(onClick = { isMenuOpen = !isMenuOpen }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                },
                backgroundColor = KColor.background,
                contentColor = KColor.secondary
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),

                ) {
                Text(
                    text = "Главная",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(Res.font.Jost))
                )
                mainCategories.forEach { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(KColor.primary, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                            .clickable { /* Navigate to category details */ },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = category,
                            fontSize = 18.sp,
                            modifier = Modifier.weight(1f),
                            color = KColor.background,
                            fontFamily = FontFamily(Font(Res.font.Jost))
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow Icon",
                            tint = KColor.background
                        )
                    }
                }
            }
        }
}