package com.smartline.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.PlaceTypeViewModel
import models.PlaceType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val viewModel = remember { PlaceTypeViewModel() }
    val placeTypes = viewModel.getPlaceTypes()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PlaceTypeScreen(placeTypes = placeTypes)
        }
    }
}

@Composable
fun PlaceTypeScreen(placeTypes: List<PlaceType>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        placeTypes.forEach { placeType ->
            Text(
                text = placeType.displayName,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { /* Здесь можно обработать выбор */ },
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
