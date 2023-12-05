package com.huntersoul.hunternotes.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.huntersoul.hunternotes.components.NavBar
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.database.NotaDataBase
import com.huntersoul.hunternotes.repository.TareaRepository
import com.huntersoul.hunternotes.viewmodel.NotaViewModel

import com.huntersoul.hunternotes.screens.AddNoteScreen
import com.huntersoul.hunternotes.screens.EditNoteScreen
import com.huntersoul.hunternotes.screens.HomeScreen
import com.huntersoul.hunternotes.screens.NotasView
import com.huntersoul.hunternotes.screens.TareasView
import com.huntersoul.hunternotes.viewmodel.TareaViewModel


@Preview(showBackground = true)
@Composable
fun NavManager(){
    val navController: NavHostController = rememberNavController()
    val db = Room.databaseBuilder(LocalContext.current, NotaDataBase::class.java, "nota_db").allowMainThreadQueries().build()
    val notaDao = db.notaDao
    val tareaDao = db.tareaDao
    val notaRepo = NotaRepository(notaDao)
    val tareaRepo = TareaRepository(tareaDao)
    val notasViewModel = NotaViewModel(notaRepo)
    val tareasViewModel = TareaViewModel(tareaRepo)

    Scaffold(
        bottomBar = {
            NavBar(navController = navController)
        }
    ) {
        NavHost(navController = navController, startDestination = "Notas", modifier = Modifier.padding(it)) {
            composable(route = "Notas") {
                NotasView()
            }
            composable(route = "Tareas") {
                TareasView()
            }
        }
    }
}


