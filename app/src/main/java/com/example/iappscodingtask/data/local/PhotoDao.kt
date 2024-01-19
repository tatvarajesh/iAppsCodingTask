package com.example.iappscodingtask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoEntity)

    @Query("SELECT * FROM photoentity")
    suspend fun getAllPhoto(): List<PhotoEntity>

    @Query("SELECT * FROM photoentity where title = :title And published = :published Limit 1")
    suspend fun getPhoto(title: String, published: String): PhotoEntity?
}