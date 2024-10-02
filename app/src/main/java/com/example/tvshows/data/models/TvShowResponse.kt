package com.example.tvshows.data.models

data class TvShowResponse(
    val page:Int,
    val pages:Int,
    val tv_shows:List<TvShow>
)
