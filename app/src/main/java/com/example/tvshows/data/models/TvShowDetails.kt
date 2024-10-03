package com.example.tvshows.data.models

data class TvShowDetails(
    val description: String,
    val genres: List<String>,
    val image_path: String,
    val name: String,
    val permalink: String,
    val pictures: List<String>,
    val rating: String,
    val runtime: Int,
    val url: String,
    val episodes:List<Episodes>
)