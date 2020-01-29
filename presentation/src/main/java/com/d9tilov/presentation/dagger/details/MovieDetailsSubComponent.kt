package com.d9tilov.presentation.dagger.details

import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
//    fun inject(movieDetailsActivity: MovieDetailsActivity)
}