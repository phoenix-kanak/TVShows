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

    // MutableLiveData to hold the TV show response
    private val _popularTvShows = MutableLiveData<TvShowResponse>()
    val popularTvShows: LiveData<TvShowResponse> get() = _popularTvShows

    //    fun getMostPopularTvShows(page: Int): LiveData<TvShowResponse> {
//        return repository.getMostPopularTvShows(page)
//    }
    fun fetchMostPopularTvShows(page: Int) {
//    viewModelScope.launch {
        val response = repository.getMostPopularTvShows(page)
        Log.d("responsevm","${response}")
        _popularTvShows.value = response.value // Assuming response.value is of type TvShowResponse
//    }
    }
}