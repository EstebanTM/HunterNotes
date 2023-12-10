package com.huntersoul.hunternotes.permissions


import android.Manifest
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.huntersoul.hunternotes.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.huntersoul.hunternotes.models.RationaleState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermisoAlmacenamiento(){
    val context = LocalContext.current
    val AccederMultimediaPermissionState = rememberPermissionState(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    var rationaleState by remember {
        mutableStateOf<RationaleState?>(null)
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.padding(150.dp))
            // Show rationale dialog when needed
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Center){
            // Show rationale dialog when needed
            rationaleState?.run { AccderMultimediaPermissionRationaleDialog(rationaleState = this) }
            if(!AccederMultimediaPermissionState.status.isGranted){
                PermissionRequestButtonAccederMultimedia(
                    isGranted = AccederMultimediaPermissionState.status.isGranted,
                    title = "Almacenamiento",
                    onClick = {
                        if (AccederMultimediaPermissionState.status.shouldShowRationale) {
                            rationaleState = RationaleState(
                                "Permiso para grabar audio",
                                "Para usar correctamente la app, debe permitir el" +
                                        "Almacenamiento." + "\n\nDesea continuar?",
                            ) { proceed ->
                                if (proceed) {
                                    AccederMultimediaPermissionState.launchPermissionRequest()
                                }
                                rationaleState = null
                            }
                        } else {
                            AccederMultimediaPermissionState.launchPermissionRequest()
                        }
                    }
                )
            }
            }
        }
    }
}
@Composable
fun PermissionRequestButtonAccederMultimedia(isGranted: Boolean, title: String,
                                             onClick: () -> Unit) {
    if (isGranted) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.CheckCircle, title, modifier = Modifier.size(48.dp))
            Spacer(Modifier.size(10.dp))
            Text(text = title, modifier = Modifier.background(Color.Transparent))
            Spacer(Modifier.size(10.dp))

        }
    } else {
        Button(onClick = onClick) {
            Text("$title")
        }
    }
}
@Composable
fun AccderMultimediaPermissionRationaleDialog(rationaleState: RationaleState) {
    AlertDialog(onDismissRequest = { rationaleState.onRationaleReply(false) }, title = {
        Text(text = rationaleState.title)
    }, text = {
        Text(text = rationaleState.rationale)
    }, confirmButton = {
        TextButton(onClick = {
            rationaleState.onRationaleReply(true)
        }) {
            Text("Continuar")
        }
    }, dismissButton = {
        TextButton(onClick = {
            rationaleState.onRationaleReply(false)
        }) {
            Text("Denegar")
        }
    })
}