package com.example.iappscodingtask.base

import com.example.iappscodingtask.common.NetworkResult
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("Error : ${response.code()} - ${response.message()}")
        } catch (e: Exception) {
            return error("Error : ${e.message ?: e.toString()}")
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Error : Api call failed $errorMessage")
}