package com.d9tilov.presentation.dagger.application

import com.d9tilov.presentation.dagger.data.DataModule
import com.d9tilov.presentation.dagger.network.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

const val SCHEDULER_MAIN_THREAD = "mainThread"
const val SCHEDULER_IO = "io"

@Singleton
@Component(
    modules = [
        (AppModule::class),
        (NetworkModule::class)
        , (DataModule::class)
        , (AndroidSupportInjectionModule::class)

    ]
)

interface MainComponent {
//    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
//    fun plus(favoriteMoviesModule: FavoriteModule): FavoritesSubComponent
//    fun plus(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent
//

}
