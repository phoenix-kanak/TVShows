package com.example.tvshows.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshows.data.models.TvShowDetailsResponse
import com.example.tvshows.domain.repository.ShowDetailRepository
import com.example.tvshows.domain.repository.TvShowRepository

class TvShowDetailsViewModel:ViewModel() {
    private val repository: ShowDetailRepository = ShowDetailRepository(ShowDetailRepository.create())

    private val _showDetails = MutableLiveData<TvShowDetailsResponse>()
    val showDetails: LiveData<TvShowDetailsResponse> get() = _showDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchShowDetails(tvShowId: Int) {
        try {
            repository.getShowDetails(tvShowId).observeForever{ response ->
                if(response!=null){
                    Log.d("TvShowsViewModel","Data fetched successfully: ${response.tvShow}")
                    _showDetails.value=response
                }else{
                    Log.d("TvShowsViewModel","Failed to fetch data")
                    _showDetails.value=null
                }

            }
        }catch (e: Exception){
            Log.e("TvShowsViewModel","Exception occurred: ${e.message}")
            _errorMessage.value="An error occurred: ${e.message}"
        }

    }
}