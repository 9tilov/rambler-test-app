package com.d9tilov.presentation.mappers

import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieEntity
import com.d9tilov.presentation.entities.Movie
import javax.inject.Inject

class MovieEntityMovieMapper @Inject
constructor() : Mapper<MovieEntity, Movie>() {

    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
    }


    override fun mapFrom(from: MovieEntity): Movie {
        return Movie(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            title = from.title,
            popularity = from.popularity,
            originalLanguage = from.originalLanguage,
            posterPath = from.posterPath?.let { posterBaseUrl + from.posterPath },
            backdropPath = from.backdropPath?.let { backdropBaseUrl + from.backdropPath },
            originalTitle = from.originalTitle,
            adult = from.adult,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
    }
}