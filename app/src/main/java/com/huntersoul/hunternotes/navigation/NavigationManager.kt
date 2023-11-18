package com.huntersoul.hunternotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.database.NotaDataBase
import com.huntersoul.hunternotes.viewmodel.NotaViewModel

import com.huntersoul.hunternotes.screens.AddNoteScreen
import com.huntersoul.hunternotes.screens.EditNoteScreen
import com.huntersoul.hunternotes.screens.HomeScreen



@Preview(showBackground = true)
@Composable
fun NavManager(){
    val navController = rememberNavController()
    val db = Room.databaseBuilder(LocalContext.current, NotaDataBase::class.java, "nota_db").allowMainThreadQueries().build()
    val dao = db.dao
    val repository = NotaRepository(dao)
    val notasViewModel = NotaViewModel(repository)

    NavHost(navController = navController, startDestination = "inicio"){
        composable(route = "inicio"){
            HomeScreen(navController, repository)
        }
        composable(route = "agregarNota"){
            AddNoteScreen(navController, viewModel = notasViewModel)
        }
        composable(route = "agregarNota/{id}"){
            val id = it.arguments?.getString("id")?.toInt()
            if (id != null) {
                EditNoteScreen(id, repository, navController, viewModel = notasViewModel)
            }
        }
    }
}


