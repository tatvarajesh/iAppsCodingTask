package com.example.iappscodingtask.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.iappscodingtask.common.NetworkResult
import com.example.iappscodingtask.databinding.ActivityPhotosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosActivity : AppCompatActivity() {

    private lateinit var activityPhotosBinding: ActivityPhotosBinding
    private val photosViewModel: PhotosViewModel by viewModels()
    private val dataAdapter: PhotoAdapter by lazy {
        PhotoAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPhotosBinding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(activityPhotosBinding.root)
        initRecyclerView()
        fetchPhotos()
    }

    private fun initRecyclerView() {
        activityPhotosBinding.rvPhotoList.adapter = dataAdapter
    }

    private fun fetchPhotos() {
        lifecycleScope.launch {
            photosViewModel.response.collectLatest { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        response.data?.let {
                            dataAdapter.submitList(it.items)
                        }
                        activityPhotosBinding.progressBar.visibility = View.GONE
                    }

                    is NetworkResult.Error -> {
                        activityPhotosBinding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@PhotosActivity, response.message, Toast.LENGTH_SHORT
                        ).show()
                    }

                    is NetworkResult.Loading -> {
                        activityPhotosBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}