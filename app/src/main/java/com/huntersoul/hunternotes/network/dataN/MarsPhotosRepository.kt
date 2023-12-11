package com.huntersoul.hunternotes.network.dataN

import com.huntersoul.hunternotes.network.modelN.MarsPhoto
import com.huntersoul.hunternotes.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>

}

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService) : MarsPhotosRepository{
    /*override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return  MarsApi.retrofitService.getPhotos()
    }*/

    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}