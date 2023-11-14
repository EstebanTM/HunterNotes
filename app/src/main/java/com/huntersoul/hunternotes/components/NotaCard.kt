package com.huntersoul.hunternotes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huntersoul.hunternotes.models.NotaEntity

@Composable
fun NotaCard(
    nota: NotaEntity,
    modifier: Modifier
){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ){
        Text(
            text = nota.titulo.toString(),
            modifier = Modifier.padding(3.dp, 5.dp,0.dp,0.dp)
        )
        Text(text = "Contenido de la nota",modifier = Modifier.padding(3.dp, 5.dp,0.dp,0.dp))

    }
}
