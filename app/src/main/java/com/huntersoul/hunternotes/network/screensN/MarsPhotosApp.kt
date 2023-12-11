
@file:OptIn(ExperimentalMaterial3Api::class)
package com.huntersoul.hunternotes.network.screensN

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.huntersoul.hunternotes.R
import com.huntersoul.hunternotes.network.viewModelN.MarsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun     MarsPhotosApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            //val marsViewModel: MarsViewModel = viewModel()
            val marsViewModel: MarsViewModel =
                viewModel(factory = MarsViewModel.Factory)

            MarsScreen(
                marsUiState = marsViewModel.marsUiState,
                marsViewModel::getMarsPhotos
            )
        }
    }
}

@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = { Text("Consumo de Api") },
        colors = TopAppBarColors(
            containerColor = colorResource(R.color.black),
            actionIconContentColor = Color.DarkGray,
            navigationIconContentColor = Color.Black,
            scrolledContainerColor = Color.DarkGray,
            titleContentColor = colorResource(R.color.white)
        )
    )
}
