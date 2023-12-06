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
fun FloatingButtonAddNota(navController: NavHostController){
    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("agregarNota")
        }){
        Icon(Icons.Default.Add, "")
        Text(text= "")
    }
}

@Composable
fun FloatingButtonAddTarea(navController: NavHostController){
    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("agregarTarea")
        }){
        Icon(Icons.Default.Add, "")
        Text(text= "")
    }
}
@Composable
fun FloatingEditarNota(navController: NavHostController){
    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("Notas")
        }){
        Icon(Icons.Default.Add, "")
        Text(text= "")
    }
}
@Composable
fun FloatingButtonEditarTarea(navController: NavHostController){
    ExtendedFloatingActionButton(
        onClick = {
            navController.navigate("Tareas")
        }){
        Icon(Icons.Default.Add, "")
        Text(text= "")
    }
}