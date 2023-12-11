package com.huntersoul.hunternotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.huntersoul.hunternotes.models.TareaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaCard(
    tarea: TareaEntity,
    navHostController: NavHostController
){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(10.dp),

        onClick = {
            navHostController.navigate("EditarTarea/${tarea.id}")
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(4.dp, 2.dp, 3.dp, 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = tarea.titulo.toString()
                        )
                        Text(
                            text = tarea.fecha ?: "",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

                            )
                    }

                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = tarea.contenido.toString()
                )
            }

        }
    }
}