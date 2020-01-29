package com.d9tilov.presentation.dagger.movie

import com.d9tilov.presentation.ui.movies.fragments.MovieListFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MoviesModule::class])
interface MovieSubComponent {
    fun inject(popularMoviesFragment: MovieListFragment)
}
