package com.example.tvshows.domain.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshows.data.models.TvShowResponse
import com.example.tvshows.data.remote.TvShowsApi
import com.example.tvshows.domain.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext

class TvShowRepository(private val tvApi: TvShowsApi) {


    companion object {
        fun create(): TvShowsApi {
            return NetworkClient().getRetrofit().create(TvShowsApi::class.java)
        }
    }

    fun getMostPopularTvShows(page: Int): LiveData<TvShowResponse> {
        val data = MutableLiveData<TvShowResponse>()

        tvApi.getMostPopularTvShows(page).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.d("response", "${response.body()}")
                } else {
                    data.value = null
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                data.value = null
                Log.e("API Error", "Failed to fetch TV shows", t)
            }
        })
        // Return LiveData, which will be updated asynchronously when the network call completes
        return data
    }

}