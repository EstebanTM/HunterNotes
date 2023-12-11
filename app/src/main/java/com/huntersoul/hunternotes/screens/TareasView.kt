package com.huntersoul.hunternotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.huntersoul.hunternotes.R
import com.huntersoul.hunternotes.components.TareaCard
import com.huntersoul.hunternotes.repository.TareaRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasView(
    navHostController: NavHostController,
    tareas: TareaRepository,

    ) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate("agregarTarea")
                }
            ){
                Icon(ImageVector.vectorResource(R.drawable.add_task_24), "")
                Text(text= "")
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tareas") },
                colors = TopAppBarColors(
                    containerColor = colorResource(R.color.black),
                    actionIconContentColor = Color.DarkGray,
                    navigationIconContentColor = Color.Black,
                    scrolledContainerColor = Color.DarkGray,
                    titleContentColor = colorResource(R.color.white)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.teal_200))
                        .padding(4.dp, 1.dp,4.dp, 1.dp),
                ) {
                    LazyColumn (
                        modifier = Modifier
                            .fillMaxSize(),
                       // contentPadding = PaddingValues(2.dp),
                    ){
                        items(tareas.obtenerTodasLasTareas()){
                            TareaCard(
                                it, navHostController
                            )
                        }

                    }
                }
            }
        }

    }
}
