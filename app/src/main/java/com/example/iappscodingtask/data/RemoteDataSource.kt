package com.example.iappscodingtask.data

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val photoApi: PhotosApi) {
    suspend fun getPhotos() = photoApi.getPhotos()
}