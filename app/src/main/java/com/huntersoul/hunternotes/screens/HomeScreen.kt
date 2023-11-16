package com.huntersoul.hunternotes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.Room
import com.huntersoul.hunternotes.components.FloatingButton
import com.huntersoul.hunternotes.components.NotaCard
import com.huntersoul.hunternotes.database.NotaDataBase
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.viewmodel.NotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, repository: NotaRepository) {
    val db = Room.databaseBuilder(LocalContext.current, NotaDataBase::class.java, "nota_db").allowMainThreadQueries().build()
    val dao = db.dao
    val repositorio = NotaRepository(dao)
    val notaViewModel = NotaViewModel(repositorio)
    val notas by remember { notaViewModel.notas }.collectAsState(emptyList())
    Scaffold(
        floatingActionButton = {
            FloatingButton(navController)
        },
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    titleContentColor = Color.Black,
                    containerColor = Color.Cyan,
                ),
                title = {
                    Text(text = "App de notas")
                },
                modifier = Modifier.padding(3.dp)
            )
        },
        bottomBar = {

        },


        ){
            padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ){
            items(notas) { nota ->
                NotaCard(nota = nota, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.size(4.dp))
            }
        }

    }
}
