package com.d9tilov.presentation

import android.app.Application
import com.d9tilov.presentation.common.Endpoint.MOVIE_URL
import com.d9tilov.presentation.common.Query.API_KEY_VALUE
import com.d9tilov.presentation.dagger.application.AppModule
import com.d9tilov.presentation.dagger.application.DaggerMainComponent
import com.d9tilov.presentation.dagger.application.MainComponent
import com.d9tilov.presentation.dagger.data.DataModule
import com.d9tilov.presentation.dagger.details.MovieDetailsModule
import com.d9tilov.presentation.dagger.details.MovieDetailsSubComponent
import com.d9tilov.presentation.dagger.movie.MovieSubComponent
import com.d9tilov.presentation.dagger.movie.MoviesModule
import com.d9tilov.presentation.dagger.network.NetworkModule
import com.facebook.stetho.Stetho
import timber.log.Timber

class App : Application() {

    private lateinit var mainComponent: MainComponent
    private var moviesComponent: MovieSubComponent? = null
    private var movieDetailsComponent: MovieDetailsSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDependencies()
        Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDependencies() {
        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(MOVIE_URL, API_KEY_VALUE))
            .dataModule(DataModule())
            .build()
    }

    fun createMoviesComponent(): MovieSubComponent {
        moviesComponent = mainComponent.plus(MoviesModule())
        return moviesComponent!!
    }


    fun releaseMoviesComponent() {
        moviesComponent = null
    }

    fun createDetailsComponent(): MovieDetailsSubComponent {
        movieDetailsComponent = mainComponent.plus(MovieDetailsModule())
        return movieDetailsComponent!!
    }

    fun releaseDetailsComponent() {
        movieDetailsComponent = null
    }
}
