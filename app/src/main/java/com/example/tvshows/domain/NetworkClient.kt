package com.example.tvshows.domain

import com.example.tvshows.common.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class NetworkClient {

    private var retrofit: Retrofit? = null
    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // You can change the converter if needed
                .build()
        }
        return retrofit!!
    }
}