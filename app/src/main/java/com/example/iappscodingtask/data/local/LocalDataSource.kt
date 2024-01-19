package com.example.iappscodingtask.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val photoDao: PhotoDao) {
    suspend fun getAllPhoto() = photoDao.getAllPhoto()
    suspend fun insertPhoto(photos:PhotoEntity) = photoDao.insertPhoto(photos)
    suspend fun getPhoto(title :String,published :String) : PhotoEntity? = photoDao.getPhoto(title,published)
}