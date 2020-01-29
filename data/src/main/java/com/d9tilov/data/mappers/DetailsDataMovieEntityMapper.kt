package com.d9tilov.data.mappers

import com.d9tilov.data.entities.DetailsData
import com.d9tilov.domain.Mapper
import com.d9tilov.domain.entities.MovieDetailsEntity
import com.d9tilov.domain.entities.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsDataMovieEntityMapper @Inject
constructor() : Mapper<DetailsData, MovieEntity>() {

    override fun mapFrom(from: DetailsData): MovieEntity {
        val movieEntity = MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            originalLanguage = from.originalLanguage,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
        val details = MovieDetailsEntity()
        details.overview = from.overview
        details.budget = from.budget
        details.homepage = from.homepage
        details.imdbId = from.imdbId
        details.revenue = from.revenue
        details.runtime = from.runtime
        details.tagline = from.tagline
        movieEntity.details = details
        return movieEntity
    }

}