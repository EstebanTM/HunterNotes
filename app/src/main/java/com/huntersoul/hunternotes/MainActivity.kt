package com.huntersoul.hunternotes

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.huntersoul.hunternotes.ui.theme.HunterNotesTheme
import com.huntersoul.hunternotes.navigation.NavManager
import com.huntersoul.hunternotes.permissions.PermisoAlmacenamiento
import com.huntersoul.hunternotes.permissions.PermisoLocalizacion
import com.huntersoul.hunternotes.permissions.PermisoNotificacion

class MainActivity : ComponentActivity() {
    //@RequiresApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HunterNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager()
                    //PermisoNotificacion()
                    PermisoAlmacenamiento()
                    PermisoLocalizacion()


                }
            }
        }
    }
}

