package com.example.tvshows.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tvshows.R
import com.example.tvshows.databinding.ActivityMainBinding
import com.example.tvshows.presentation.viewmodel.TvShowsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tvShowViewModel: TvShowsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        tvShowViewModel.popularTvShows.observe(this) { response ->
            response?.let {
//                tvShowAdapter.setTvShows(it.results) // Assuming results is a List<TvShow>
            }
        }
        loadPopularTvShows(1)
    }
    private fun loadPopularTvShows(page: Int) {
        tvShowViewModel.fetchMostPopularTvShows(page)
    }
}