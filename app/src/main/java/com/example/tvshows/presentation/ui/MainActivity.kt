package com.example.tvshows.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.data.models.TvShow
import com.example.tvshows.databinding.ActivityMainBinding
import com.example.tvshows.presentation.adapters.TVShowsAdapter
import com.example.tvshows.presentation.viewmodel.TvShowsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tvShowViewModel: TvShowsViewModel by viewModels()
    private val tvShowsList: MutableList<TvShow> = mutableListOf()
    private lateinit var tvShowsAdapter: TVShowsAdapter
    private lateinit var shows_rv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shows_rv = binding.tvShowRv
        shows_rv.layoutManager = LinearLayoutManager(this)

        tvShowViewModel.popularTvShows.observe(this) { response ->
            binding.progressBar.visibility = View.INVISIBLE
            if (response != null && response.tv_shows.isNotEmpty()) {
                Log.d("id123","$response")
                tvShowsList.addAll(response.tv_shows)
                tvShowsAdapter = TVShowsAdapter(tvShowsList)
                shows_rv.adapter = tvShowsAdapter
                tvShowsAdapter.notifyDataSetChanged()
            }
        }
        getPopularTvShows()
    }

    private fun getPopularTvShows() {
        binding.progressBar.visibility = View.VISIBLE
        tvShowViewModel.fetchMostPopularTvShows(1)

//        tvShowViewModel.popularTvShows.observe(this) { reponse ->
//            binding.progressBar.visibility = View.INVISIBLE
//            if (reponse != null) {
//                Log.d("MainActivity1", "onCreate: ${reponse.tv_shows}")
//                tvShowsList.add(TvShow("India","20-12-2021",12,"https://static.episodate.com/images/tv-show/thumbnail/35624.jpg","Movie name","network","linkperma","12-09-9980","okay"))
//            }

    }
}

//    private fun loadPopularTvShows(page: Int) {
//        tvShowViewModel.fetchMostPopularTvShows(page)
//    }
