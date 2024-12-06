package org.smartline.app.views.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.smartline.app.KColor

@Composable
fun MainScreenView() {
    var isMenuOpen by remember { mutableStateOf(false) }
    val menuItems = listOf("Главная", "Настройки", "История", "О нас")
    val mainCategories = listOf("Финансы", "Развлечения", "Медицина", "Салон красоты")

    Row(Modifier.fillMaxSize()) {
        // Side Menu
        if (isMenuOpen) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .background(KColor.secondary),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Привет!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
                IconButton(onClick = { /* Add profile action */ }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Icon",
                        tint = KColor.background
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                menuItems.forEach { menuItem ->
                    Text(
                        text = menuItem,
                        fontSize = 16.sp,
                        color = KColor.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Handle navigation */ }
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .background(KColor.primary, shape = RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    )
                }
            }
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
                contentColor = Color.Black
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Главная",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
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
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow Icon",
                            tint = Color.Black
                        )
                    }
                }
            }

            // Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("О нас")
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Наши соц. сети: ")
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Instagram Icon",
                        tint = Color.Magenta
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Возникли проблемы?")
            }
        }
    }
}