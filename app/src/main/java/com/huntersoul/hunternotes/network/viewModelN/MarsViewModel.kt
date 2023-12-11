package com.huntersoul.hunternotes.network.viewModelN


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.huntersoul.hunternotes.alarmManager.AlarmApp
//import com.huntersoul.hunternotes.MarsPhotosApplication
import com.huntersoul.hunternotes.network.dataN.AppContainer
import com.huntersoul.hunternotes.network.dataN.DefaultAppContainer
import com.huntersoul.hunternotes.network.dataN.MarsPhotosRepository
import com.huntersoul.hunternotes.network.dataN.NetworkMarsPhotosRepository
import com.huntersoul.hunternotes.network.modelN.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: List< MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}
class MarsViewModel(
    private val marsPhotosRepository: MarsPhotosRepository

) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    /*var marsUiState: String by mutableStateOf("")
        private set*/


    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set


    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        //marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            marsUiState =  try {
                /*val listResult = MarsApi.retrofitService.getPhotos()
                listResult.forEach { Log.d("${it.id}", "${it.img_src}" ) }*/
                //val marsPhotosRepository = NetworkMarsPhotosRepository(retrofitService)
                //val marsPhotosRepository = DefaultAppContainer().marsPhotosRepository
                val listResult = marsPhotosRepository.getMarsPhotos()
                MarsUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                MarsUiState.Error
            }

        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AlarmApp)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }

}
