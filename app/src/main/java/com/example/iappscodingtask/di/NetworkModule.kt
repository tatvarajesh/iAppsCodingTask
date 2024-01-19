package com.example.iappscodingtask.di

import com.example.iappscodingtask.common.BASE_URL
import com.example.iappscodingtask.data.remote.PhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun providePhotosApiService(retrofit: Retrofit): PhotosApi =
        retrofit.create(PhotosApi::class.java)

}