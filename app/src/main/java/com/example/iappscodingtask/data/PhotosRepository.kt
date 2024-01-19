package com.example.iappscodingtask.data

import com.example.iappscodingtask.base.BaseApiResponse
import com.example.iappscodingtask.common.NetworkResult
import com.example.iappscodingtask.model.PhotosResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class PhotosRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getPhotos(): Flow<NetworkResult<PhotosResponse>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.getPhotos() })
        }.flowOn(Dispatchers.IO)
    }
}