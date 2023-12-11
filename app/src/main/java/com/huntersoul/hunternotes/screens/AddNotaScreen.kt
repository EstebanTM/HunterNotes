package com.huntersoul.hunternotes.screens

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.work.WorkManager
import coil.compose.AsyncImage
import com.huntersoul.hunternotes.R
import com.huntersoul.hunternotes.components.NotificationWorker
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.viewmodel.NotaViewModel
import java.time.LocalDateTime
import androidx.work.OneTimeWorkRequestBuilder



@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNoteScreen(
    navHostController: NavHostController,
    viewModel: NotaViewModel
){


    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"

    var uri : Uri? = null

    var hasImage by remember {
        mutableStateOf(false)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    var entity = NotaEntity(
        id = 0,
        titulo = titulo,
        contenido = descripcion,
        multimedia = imageUri.toString(),
        fecha = fechaActual
    )
    Scaffold(
        floatingActionButton = {
            Column(){
                Row(){

                }
                Spacer(modifier = Modifier.padding(4.dp))
                Row(){
                    FloatingActionButton(onClick = {
                        imagePicker.launch("image/*")
                    }){
                        Icon(ImageVector.vectorResource(R.drawable.gallery), null)
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Row(){
                    FloatingActionButton(onClick = {
                        viewModel.guardarNota(entity)
                        onAcceptButtonClicked()
                        navHostController.popBackStack()
                    }){
                        Icon(Icons.Default.Check, null)
                    }
                }

            }

        },
        topBar = {
            TopAppBar(
                title = {
                    Text("Agregar Nota")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }){
                        Icon(Icons.AutoMirrored.Default.ArrowBack, null)
                    }
                }
            )
        },
    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = colorResource(R.color.teal_200))
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Box(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .padding(16.dp),
                ){
                    if (hasImage && imageUri != null){
                        if (hasImage){
                            AsyncImage(
                                model = imageUri,
                                contentDescription = null,
                                placeholder = painterResource(R.drawable.ic_launcher_background),
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(250.dp)
                                    .clickable {
                                        imagePicker.launch("image/*")
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

            }


        }
    }

}
private fun onAcceptButtonClicked() {
    // Trigger the notification worker when the button is clicked
    val notificationWorkRequest =OneTimeWorkRequestBuilder<NotificationWorker>().build()

    WorkManager.getInstance().enqueue(notificationWorkRequest)
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditNoteScreen(
    id: Int,
    repository: NotaRepository,
    navHostController: NavHostController,
    viewModel: NotaViewModel,
){
    var nota = repository.obtenerNota(id)
    var titulo by remember { mutableStateOf(nota.titulo) }
    var descripcion by remember { mutableStateOf(nota.contenido) }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"
    var uri : Uri? = null
    var hasImage by remember {
        mutableStateOf(nota.multimedia != null)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(nota.multimedia?.toUri())
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            hasImage = it != null
            imageUri = it
        }
    )

    var entity = NotaEntity(
        id = id,
        titulo = titulo,
        contenido = descripcion,
        multimedia = imageUri.toString(),
        fecha = fechaActual,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Editar nota")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }){
                        Icon(Icons.AutoMirrored.Default.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.eliminarNota(entity)
                        navHostController.popBackStack()
                    }){
                        Icon(Icons.Default.Delete, null)
                    }
                }

            )
        },
        floatingActionButton = {
            Column(){
                Row(){
                    FloatingActionButton(onClick = {
                        imagePicker.launch("image/*")
                    }){
                        Icon(ImageVector.vectorResource(R.drawable.gallery), null)
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Row(){
                    FloatingActionButton(onClick = {
                        viewModel.actualizarNota(entity)
                        onAcceptButtonClicked()
                        navHostController.popBackStack()
                    }){
                        Icon(Icons.Default.Check, null)
                    }
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.teal_200))
                .padding(it)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = titulo.toString(),
                onValueChange = { titulo = it },
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = descripcion.toString(),
                onValueChange = { descripcion = it },
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Box(
                    modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
                    .padding(16.dp)
                ){
                    if (hasImage && imageUri != null){
                        if (hasImage){
                            AsyncImage(
                                model = imageUri,
                                contentDescription = null,
                                placeholder = painterResource(R.drawable.ic_launcher_background),
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(250.dp)
                                    .clickable {
                                        imagePicker.launch("image/*")
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

            }
        }
    }
}
