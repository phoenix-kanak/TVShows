package com.example.tvshows.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshows.data.models.TvShowResponse
import com.example.tvshows.data.remote.TvShowsApi
import com.example.tvshows.domain.repository.TvShowRepository

class TvShowsViewModel : ViewModel() {
    private val repository: TvShowRepository = TvShowRepository(TvShowRepository.create())

    private val _popularTvShows = MutableLiveData<TvShowResponse>()
    val popularTvShows: LiveData<TvShowResponse> get() = _popularTvShows

    fun fetchMostPopularTvShows(page: Int) {
        repository.getMostPopularTvShows(page).observeForever { response ->
            if (response != null) {
                Log.d("TvShowsViewModel", "Data fetched successfully: ${response.tv_shows}")
                _popularTvShows.value = response
            } else {
                Log.d("TvShowsViewModel", "Failed to fetch data")
                _popularTvShows.value = null
            }
        }
    }
}