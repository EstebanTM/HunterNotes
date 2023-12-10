package com.huntersoul.hunternotes.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.huntersoul.hunternotes.screens.AddTareaScreen
import com.huntersoul.hunternotes.screens.EditNoteScreen
import com.huntersoul.hunternotes.screens.EditTareaScreen
import com.huntersoul.hunternotes.screens.NotasView
import com.huntersoul.hunternotes.screens.TareasView

import com.huntersoul.hunternotes.viewmodel.TareaViewModel



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NavManager(){
    val navController: NavHostController = rememberNavController()
    val db = Room.databaseBuilder(LocalContext.current, NotaDataBase::class.java, "nota_db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
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
                NotasView(navController, notaRepo)
            }
            composable(route = "AgregarNota") {
                AddNoteScreen(navController, notasViewModel)
            }
            composable(route = "EditarNota/{id}"){
                val id = it.arguments?.getString("id")?.toInt()
                if(id != null){
                    EditNoteScreen(id, notaRepo, navController, notasViewModel)
                }
            }
            composable(route = "Tareas") {
                TareasView(navController, tareaRepo)
            }
            composable(route = "AgregarTarea") {
                AddTareaScreen(navController, tareasViewModel)
            }
            composable(route = "EditarTarea/{id}"){
                val id = it.arguments?.getString("id")?.toInt()
                if(id != null){
                    EditTareaScreen(id, tareaRepo, navController, tareasViewModel)
                }
            }

        }
    }
}


