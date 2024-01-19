package com.example.iappscodingtask.di

import android.content.Context
import androidx.room.Room
import com.example.iappscodingtask.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "photos_db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePhotoDao(appDatabase: AppDatabase) = appDatabase.photoDao
}