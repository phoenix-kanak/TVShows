package com.example.tvshows.data.remote

import com.example.tvshows.data.models.TvShowDetailsResponse
import com.example.tvshows.data.models.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowsApi {

    @GET("most-popular")
    fun getMostPopularTvShows(@Query("page") page: Int): Call<TvShowResponse>

    @GET("show-details")
    fun getShowDetails(@Query("q") tvShowId: Int): Call<TvShowDetailsResponse>

}