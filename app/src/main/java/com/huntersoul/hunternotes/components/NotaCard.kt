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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.huntersoul.hunternotes.models.NotaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotaCard(
    nota: NotaEntity,
    navHostController: NavHostController
){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
        onClick = {
            navHostController.navigate("EditarNota/${nota.id}")
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(3.dp, 2.dp, 3.dp, 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                Text(
                    text = nota.titulo.toString(),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = nota.contenido.toString()
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(5.dp, 0.dp),
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = nota.fecha.toString(),
                    textAlign = TextAlign.End,
                    fontSize = 10.sp,
                )
            }
        }
    }
}
