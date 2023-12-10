package com.huntersoul.hunternotes.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.huntersoul.hunternotes.alarmManager.AlarmItem
import com.huntersoul.hunternotes.alarmManager.AlarmScheduler
import com.huntersoul.hunternotes.alarmManager.AlarmSchedulerImpl
import com.huntersoul.hunternotes.models.TareaEntity
import java.time.LocalDateTime




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Alarma(
entity:TareaEntity
){
    var secondText by remember {
        mutableStateOf("")
    }
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(LocalContext.current)
    var alarmItem: AlarmItem? = null
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(value = secondText, onValueChange = {
            secondText = it
        },
            label = {
                Text(text = "Alarma")
            }
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            alarmItem?.let(alarmScheduler::cancel)
        }) {
            Text(text = "Cancelar")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            if(secondText != ""){
                alarmItem = AlarmItem(
                    time = LocalDateTime.now().plusSeconds(
                        secondText.toLong()
                    ),
                    title = "La tarea debe realizarse:",
                    message = entity.titulo
                )
                alarmItem?.let(alarmScheduler::schedule)
                secondText = ""
            }
        }) {
            Text(text = "Aceptar")
        }
    }
}