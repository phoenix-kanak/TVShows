package com.example.tvshows.presentation.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.common.listeners.TvShowsListener
import com.example.tvshows.data.models.TvShow
import com.example.tvshows.databinding.ActivityMainBinding
import com.example.tvshows.presentation.adapters.TVShowsAdapter
import com.example.tvshows.presentation.viewmodel.TvShowsViewModel
import java.util.Locale

class MainActivity : AppCompatActivity(), TvShowsListener {
    private lateinit var binding: ActivityMainBinding
    private val tvShowViewModel: TvShowsViewModel by viewModels()
    private val tvShowsList: MutableList<TvShow> = mutableListOf()
    private lateinit var tvShowsAdapter: TVShowsAdapter
    private lateinit var shows_rv: RecyclerView
    private lateinit var searchedItems: ArrayList<TvShow>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shows_rv = binding.tvShowRv
        shows_rv.layoutManager = LinearLayoutManager(this)
        searchedItems = arrayListOf()
        tvShowViewModel.popularTvShows.observe(this) { response ->
            binding.progressBar.visibility = View.INVISIBLE
            if (response != null && response.tv_shows.isNotEmpty()) {
                Log.d("id123", "$response")
                tvShowsList.addAll(response.tv_shows)
                searchedItems.addAll(tvShowsList)
                tvShowsAdapter = TVShowsAdapter(searchedItems, this)
                shows_rv.adapter = tvShowsAdapter
                tvShowsAdapter.notifyDataSetChanged()
            }
        }
        tvShowViewModel.errorMessage.observe(this) { errorMessage ->
            binding.progressBar.visibility = View.INVISIBLE
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
        getPopularTvShows()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchedItems.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    tvShowsList.forEach {
                        if (it.name.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchedItems.add(it)
                        }
                    }
                    tvShowsAdapter.notifyDataSetChanged()
                } else {
                    searchedItems.clear()
                    searchedItems.addAll(tvShowsList)
                    tvShowsAdapter.notifyDataSetChanged()
                }
                return false
            }

        })
    }


    private fun getPopularTvShows() {
        binding.progressBar.visibility = View.VISIBLE
        tvShowViewModel.fetchMostPopularTvShows(1)

    }

    override fun onTvShowClicked(tvShow: TvShow) {
        val intent = Intent(this, TvShowDetailActivity::class.java)
        intent.putExtra("tvShowId", tvShow.id)
        intent.putExtra("name", tvShow.name)
        intent.putExtra("startDate", tvShow.start_date)
        intent.putExtra("country", tvShow.country)
        intent.putExtra("network", tvShow.network)
        intent.putExtra("showImg", tvShow.image_thumbnail_path)
        startActivity(intent)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            .setNegativeButton("No") { dialog, which ->
            }
            .show()
    }
}

