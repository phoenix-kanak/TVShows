package com.example.tvshows.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshows.data.models.TvShowDetailsResponse
import com.example.tvshows.data.remote.TvShowsApi
import com.example.tvshows.domain.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDetailRepository(private val tvApi: TvShowsApi) {
    companion object {
        fun create(): TvShowsApi {
            return NetworkClient().getRetrofit().create(TvShowsApi::class.java)
        }
    }

    fun getShowDetails(tvShowId: Int): LiveData<TvShowDetailsResponse> {
        val data = MutableLiveData<TvShowDetailsResponse>()
        tvApi.getShowDetails(tvShowId).enqueue(object : Callback<TvShowDetailsResponse> {
            override fun onResponse(
                call: Call<TvShowDetailsResponse>,
                response: Response<TvShowDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.d("response", "${response.body()}")
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<TvShowDetailsResponse>, t: Throwable) {
                data.value = null
                Log.e("API Error", "Failed to fetch TV shows", t)
            }
        })
        return data
    }
}