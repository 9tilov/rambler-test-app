package com.d9tilov.presentation.dagger.details

import com.d9tilov.presentation.ui.detail.MovieDetailsFragment
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
    fun inject(fragment: MovieDetailsFragment)
}
