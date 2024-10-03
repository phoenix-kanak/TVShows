package com.example.tvshows.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tvshows.databinding.ActivityTvShowDetailBinding
import com.example.tvshows.presentation.viewmodel.TvShowDetailsViewModel
import com.squareup.picasso.Picasso

class TvShowDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowDetailBinding
    private val tvShowDetailsViewModel: TvShowDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tvShowDetailsViewModel.showDetails.observe(this) { response ->
            binding.progressBar.visibility = View.INVISIBLE
            if (response != null && response.tvShow.url.isNotEmpty()) {
                Log.d("desc", "${response.tvShow}")
                binding.showDescription.text = Html.fromHtml(response.tvShow.description)
            }
        }
        tvShowDetailsViewModel.errorMessage.observe(this) { errorMessage ->
            binding.progressBar.visibility = View.INVISIBLE
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
        loadBasicDetails()

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun loadBasicDetails() {
        binding.progressBar.visibility = View.VISIBLE
        val tvShowId = intent.getIntExtra("tvShowId", 0)
        binding.tvShowName.text = intent.getStringExtra("name")
        binding.tvShowCountry.text = intent.getStringExtra("country")
        binding.networkStatus.text = intent.getStringExtra("network")
        binding.showStartDate.text = intent.getStringExtra("startDate")
        val imageUriString = intent.getStringExtra("showImg")
        Log.d("image", "$imageUriString")
        if (imageUriString != null) {
            Picasso.get().load(imageUriString).into(binding.showImage)
        }
        tvShowDetailsViewModel.fetchShowDetails(tvShowId)
    }
}