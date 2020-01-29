package com.d9tilov.presentation.dagger.details

import com.d9tilov.domain.MoviesRepository
import com.d9tilov.domain.usecases.GetMovieDetails
import com.d9tilov.presentation.common.ASyncTransformer
import com.d9tilov.presentation.mappers.MovieEntityMovieMapper
import com.d9tilov.presentation.ui.detail.MovieDetailsVMFactory
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule {

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: MoviesRepository): GetMovieDetails {
        return GetMovieDetails(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideMovieDetailsVMFactory(
        getMovieDetails: GetMovieDetails,
        mapper: MovieEntityMovieMapper
    ): MovieDetailsVMFactory {
        return MovieDetailsVMFactory(
            getMovieDetails,
            mapper
        )
    }
}