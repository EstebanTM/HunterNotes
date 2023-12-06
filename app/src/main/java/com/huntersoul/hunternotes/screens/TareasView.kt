package com.huntersoul.hunternotes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.huntersoul.hunternotes.R
import com.huntersoul.hunternotes.components.FloatingButtonAddTarea
import com.huntersoul.hunternotes.components.NotaCard
import com.huntersoul.hunternotes.components.TareaCard
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.repository.TareaRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasView(
    navHostController: NavHostController,
    tareas: TareaRepository,

    ) {
    Scaffold(
        floatingActionButton = {
            FloatingButtonAddTarea(navHostController)
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
