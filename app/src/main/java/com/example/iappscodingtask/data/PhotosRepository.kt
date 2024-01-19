package com.example.iappscodingtask.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.iappscodingtask.base.BaseApiResponse
import com.example.iappscodingtask.common.NetworkResult
import com.example.iappscodingtask.data.local.LocalDataSource
import com.example.iappscodingtask.data.local.toItemModel
import com.example.iappscodingtask.data.remote.RemoteDataSource
import com.example.iappscodingtask.model.PhotosResponse
import com.example.iappscodingtask.model.toPhotoEntityModelList
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class PhotosRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val application: Application
) : BaseApiResponse() {
    suspend fun getPhotos(): Flow<NetworkResult<PhotosResponse>> {

        // fetch data from api and store it in DB
        if (isNetworkAvailable(application)) {
            val resp: NetworkResult<PhotosResponse> = safeApiCall {
                remoteDataSource.getPhotos()
            }
            if (resp is NetworkResult.Success) {
                resp.data?.let {
                    it.items?.toPhotoEntityModelList()?.forEach { photo ->
                        // check already exists in DB or not
                        if (localDataSource.getPhoto(
                                photo.title, photo.published
                            ) == null
                        ) {
                            localDataSource.insertPhoto(
                                photo
                            )
                        }
                    }
                }
            }
        }
        // fetch all the data from local db
        return flow {
            emit(
                NetworkResult.Success(
                    PhotosResponse(
                        items = localDataSource.getAllPhoto().map {
                            it.toItemModel()
                        }
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}