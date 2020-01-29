package com.d9tilov.presentation.dagger.movie

import com.d9tilov.domain.MoviesRepository
import com.d9tilov.domain.usecases.GetPopularMovies
import com.d9tilov.presentation.common.ASyncTransformer
import com.d9tilov.presentation.mappers.MovieEntityMovieMapper
import com.d9tilov.presentation.ui.movies.fragments.PopularMoviesVMFactory
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {

    @MovieScope
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMovies {
        return GetPopularMovies(ASyncTransformer(), moviesRepository)
    }

    @MovieScope
    @Provides
    fun providePopularMoviesVMFactory(useCase: GetPopularMovies, mapper: MovieEntityMovieMapper)
            : PopularMoviesVMFactory {
        return PopularMoviesVMFactory(useCase, mapper)
    }
}
