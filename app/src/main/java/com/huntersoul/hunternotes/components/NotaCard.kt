package com.huntersoul.hunternotes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.huntersoul.hunternotes.models.NotaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotaCard(
    nota: NotaEntity,
    navHostController: NavHostController,
    modifier: Modifier
){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        onClick = {
            navHostController.navigate("agregarNota/${nota.id}")
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                text = nota.titulo.toString()
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = nota.descripcion.toString()
                )
            }
        }
    }
}
