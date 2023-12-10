package com.huntersoul.hunternotes.screens

import android.os.Build
import com.huntersoul.hunternotes.models.TareaEntity
import com.huntersoul.hunternotes.repository.TareaRepository
import com.huntersoul.hunternotes.viewmodel.TareaViewModel

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.huntersoul.hunternotes.R
import com.huntersoul.hunternotes.components.Alarma
import com.huntersoul.hunternotes.components.OSMComposeMapa
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTareaScreen(
    navHostController: NavHostController,
    viewModel: TareaViewModel
){
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    calendar.set(LocalDate.now().year, LocalDate.now().monthValue-1, LocalDate.now().dayOfMonth-1)
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    var mapa by remember { mutableStateOf(false) }
    var localizacion by remember { mutableStateOf("") }
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    var entity = TareaEntity(
        id = 0,
        titulo = titulo,
        contenido = descripcion,
        fecha = formatter.format(Date(selectedDate.plus(86400000))),
        localizacion = localizacion
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Agregar tarea")
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
        floatingActionButton = {
            Column(
                modifier = Modifier
                  //  .fillMaxWidth()
                    .padding(4.dp),
                //verticalArrangement = Arrangement.Center
            ){
                Row(){
                    FloatingActionButton(
                        onClick = {
                            mapa=true
                        }){
                        Icon(Icons.Default.LocationOn, null)
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(){
                    FloatingActionButton(
                        onClick = {
                            viewModel.guardarTarea(entity)
                            navHostController.popBackStack()
                        }
                    ){
                        Icon(Icons.Default.Check, null)
                    }
                }

            }

        },

    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = colorResource(R.color.teal_200))
        ) {
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = localizacion,
                onValueChange = { localizacion = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("localizacion") },
                placeholder = { Text("Localizacion") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            showDatePicker = false
                            selectedDate = datePickerState.selectedDateMillis!!
                        }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDatePicker = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = {
                        showDatePicker = true
                    }
                ) {
                    Text(text = formatter.format(Date(selectedDate.plus(86400000))))
                }
            }
            Alarma(entity)
            if (mapa) {
                AlertDialog(
                    onDismissRequest = {
                        mapa = false
                    },
                    text = {
                        // Contenido del diálogo
                        OSMComposeMapa(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            onLocationSelected = { ubicacion ->
                                localizacion = ubicacion.toString()
                            }
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                //localizacion = selectedLocation.toString()
                                mapa = false
                            }
                        ) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTareaScreen(
    id: Int,
    repository: TareaRepository,
    navHostController: NavHostController,
    viewModel: TareaViewModel,
){
    var tarea = repository.obtenerTarea(id)

    //val multiViewModel = MultimediaViewModel(multiRepository, id)
    var titulo by remember { mutableStateOf(tarea.titulo) }
    var descripcion by remember { mutableStateOf(tarea.contenido) }
    var fecha by remember { mutableStateOf(tarea.fecha) }
    var localizacion by remember { mutableStateOf(tarea.localizacion) }

    val calendar = Calendar.getInstance()
    calendar.set(LocalDate.now().year, LocalDate.now().monthValue-1, LocalDate.now().dayOfMonth) // add year, month (Jan), date
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    var mapa by remember { mutableStateOf(false) }
    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis) // or use mutableStateOf(calendar.timeInMillis)
    }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    var entity = TareaEntity(
        id = id,
        titulo = titulo,
        contenido = descripcion,
        fecha = formatter.format(Date(selectedDate.plus(86400000))),
        localizacion = localizacion,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Editar tarea")
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
                        viewModel.eliminarTarea(entity)
                        navHostController.popBackStack()
                    }){
                        Icon(Icons.Default.Delete, null)
                    }
                }

            )
        },
        floatingActionButton = {
            Column(
                modifier = Modifier
                    //  .fillMaxWidth()
                    .padding(4.dp),
                //verticalArrangement = Arrangement.Center
            ){
                Row(){
                    FloatingActionButton(
                        onClick = {
                            mapa = true
                        }){
                        Icon(Icons.Default.LocationOn, null)
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(){
                    FloatingActionButton(
                        onClick = {
                            viewModel.guardarTarea(entity)
                            navHostController.popBackStack()
                        }
                    ){
                        Icon(Icons.Default.Check, null)
                    }
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(R.color.teal_200))
        ) {
            TextField(
                value = titulo.toString(),
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = descripcion.toString(),
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = localizacion.toString(),
                onValueChange = { localizacion = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("localizacion") },
                placeholder = { Text("Localizacion") }
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            showDatePicker = false
                            selectedDate = datePickerState.selectedDateMillis!!
                        }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDatePicker = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = {
                        showDatePicker = true
                    }
                ) {
                    //Text(text = formatter.format(Date(selectedDate.plus(86400000))))
                    fecha?.let { it1 -> Text(text = it1) }
                }
            }
            Alarma(entity)
            if (mapa) {
                AlertDialog(
                    onDismissRequest = {
                        mapa = false
                    },
                    text = {
                        // Contenido del diálogo
                        OSMComposeMapa(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            onLocationSelected = { ubicacion ->
                                // Actualiza la variable de ubicación cuando se selecciona una ubicación en el mapa
                                localizacion = ubicacion.toString()
                            }
                        )
                    },
                    confirmButton = {
                        Button(
                            modifier = Modifier,
                            onClick = {
                                //  localizacion = selectedLocation.toString()
                                mapa = false
                            }
                        ) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}
@Composable
fun LocationButton() {

    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permisos otorgados, puedes abrir el mapa
        } else {
            // El usuario ha denegado los permisos, puedes mostrar un mensaje o realizar otras acciones
            Toast.makeText(
                context,
                "Es necesario otorgar permisos de ubicación para utilizar esta función",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Spacer(modifier = Modifier.padding(3.dp))
    Row {
        // Definir la función internamente para evitar pasar demasiados parámetros
        FloatingActionButton(
            onClick = {
                requestLocationPermission(context, requestPermissionLauncher)
            }
        ) {
            Icon(Icons.Default.LocationOn, null)
        }
    }
}

fun requestLocationPermission(
    context: Context,
    requestPermissionLauncher: ActivityResultLauncher<String>
) {
    when {
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            // Los permisos ya están otorgados, puedes abrir el mapa directamente
        }
        else -> {
            // Solicitar permisos al usuario
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}






