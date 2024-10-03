package com.example.tvshows.common.listeners

import com.example.tvshows.data.models.TvShow

interface TvShowsListener {
    fun onTvShowClicked(tvShow: TvShow)
}