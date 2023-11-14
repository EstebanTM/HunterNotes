package com.huntersoul.hunternotes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FloatingButton(){
    ExtendedFloatingActionButton(
        onClick = {

        }){
        Icon(Icons.Default.Add, "")
        Text(text= "")
    }
}