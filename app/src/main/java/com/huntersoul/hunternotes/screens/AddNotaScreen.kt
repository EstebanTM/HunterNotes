package com.huntersoul.hunternotes.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.huntersoul.hunternotes.components.TopBarComponent
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.viewmodel.NotaViewModel

//@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNoteScreen(navController: NavController, viewModel: NotaViewModel){
    var titulo by remember { mutableStateOf("") }
    var contenido by rememberSaveable { mutableStateOf("") }

    //Base de datos
    var entity = NotaEntity(
        id=0,
        titulo = titulo,
        contenido = contenido,
        multimedia = null
    )
    Scaffold(
        topBar = {
            TopBarComponent("Agregar Nota", navController, viewModel, entity)
        }

    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            androidx.compose.material3.TextField(
                value = titulo,
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth(),
                label = { androidx.compose.material3.Text("Titulo") },
                placeholder = { androidx.compose.material3.Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(7.dp))
            androidx.compose.material3.TextField(
                value = contenido,
                onValueChange = { contenido = it },
                modifier = Modifier.fillMaxSize(),
                label = { androidx.compose.material3.Text("Descripcion") },
                placeholder = { androidx.compose.material3.Text("Descripcion") }
            )
        }
    }

}



