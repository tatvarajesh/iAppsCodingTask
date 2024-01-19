package com.example.iappscodingtask.data.remote

import com.example.iappscodingtask.common.*
import com.example.iappscodingtask.model.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET(api_get_photos)
    suspend fun getPhotos(
        @Query(param_format) format: String = "json",
        @Query(param_tags) tag: String = "cat",
        @Query(param_nojsoncallback) noJsonCallBack: Int = 1
    ): Response<PhotosResponse>

}