package com.d9tilov.data.mappers

import com.d9tilov.data.entities.MovieData
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieEntity
import javax.inject.Inject

class MovieDataEntityMapper @Inject
constructor() : Mapper<MovieData, MovieEntity>() {

    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalLanguage = from.originalLanguage,
            backdropPath = from.backdropPath,
            originalTitle = from.originalTitle,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
    }
}
