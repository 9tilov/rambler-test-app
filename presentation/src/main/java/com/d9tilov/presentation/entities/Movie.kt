package com.d9tilov.presentation.entities

data class Movie (

    var id: Long = 0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var title: String,
    var popularity: Double = 0.0,
    var posterPath: String? = null,
    var originalLanguage: String,
    var originalTitle: String,
    var backdropPath: String? = null,
    var adult: Boolean = false,
    var releaseDate: String,
    var isFavorite: Boolean = false,
    var overview: String? = null)
