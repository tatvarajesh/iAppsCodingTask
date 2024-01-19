package com.example.iappscodingtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iappscodingtask.common.NetworkResult
import com.example.iappscodingtask.common.toFormatDatePublished
import com.example.iappscodingtask.data.PhotosRepository
import com.example.iappscodingtask.model.Items
import com.example.iappscodingtask.model.PhotosResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository,
) : ViewModel() {

    private val _response = MutableSharedFlow<NetworkResult<PhotosResponse>>(1)
    val response = _response.asSharedFlow()

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            repository.getPhotos().collectLatest { response ->
                if (response is NetworkResult.Success) {
                    response.data?.items?.let {
                        response.data.items = sortDataByPublished(it)
                    }
                }
                _response.emit(response)
            }
        }
    }

    private fun sortDataByPublished(it: List<Items>): List<Items> {
        return it.sortedByDescending {
            it.published?.toFormatDatePublished()
        }
    }
}
