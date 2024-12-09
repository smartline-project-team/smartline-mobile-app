package org.smartline.app.views.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.smartline.app.KColor

@Composable
fun MenuView() {
    val menuItems = listOf("Главная", "Настройки", "История", "О нас")
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(KColor.secondary),

        ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Привет!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp).align(Alignment.Top)
                )
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd,
                ){
                    IconButton(
                        onClick = { /* Add profile action */ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Icon",
                            tint = KColor.background,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        .padding(12.dp),
                )
            }
        }

    }
}