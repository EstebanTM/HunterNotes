package com.huntersoul.hunternotes.network.screensN


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.size.Scale

import com.huntersoul.hunternotes.R
import com.huntersoul.hunternotes.network.modelN.MarsPhoto
import com.huntersoul.hunternotes.network.viewModelN.MarsUiState

@Composable
fun MarsScreen(
    marsUiState: MarsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
        .background(color = colorResource(R.color.teal_200))
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is MarsUiState.Success -> PhotosGridScreen(photos = marsUiState.photos, modifier)

        is MarsUiState.Error -> ErrorScreen( retryAction,   modifier = modifier.fillMaxSize())
    }

}

@Composable
fun PhotosGridScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos , key = {photo -> photo.id} ) {
                photo ->
            MarsPhotoCard(photo = photo,
                modifier = modifier.
                padding(4.dp).fillMaxWidth().aspectRatio(1.5f)
            )
        }
    }

}


@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .background(Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

    ) {

        AsyncImage(
            model = coil.request.ImageRequest.Builder(context = LocalContext.current)
                .data(photo.img_src)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            error = painterResource(id = R.drawable.outline_broken_image_24),
            placeholder = painterResource(id = R.drawable.outline_downloading),
            contentDescription = "Foto de marte",
            modifier = Modifier.fillMaxSize().background(Color.Transparent)
        )
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.outline_downloading),
        contentDescription = ("Cargando")
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.outline_broken_image_24), contentDescription = ""
        )
        Text(
            text = "Descarga fallida",
            modifier = Modifier.padding(16.dp),
            color = Color.Black
        )
        Button(
            onClick = retryAction,
            ) {
            Text("Reintentar")

        }

    }
}
