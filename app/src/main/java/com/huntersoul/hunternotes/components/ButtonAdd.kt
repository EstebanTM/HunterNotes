package com.huntersoul.hunternotes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun FloatingButton(navController: NavHostController){
    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("agregarNota")
        }){
        Icon(Icons.Default.Add, "")
        Text(text= "")
    }
}