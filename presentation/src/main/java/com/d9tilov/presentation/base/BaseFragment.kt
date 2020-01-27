package com.d9tilov.presentation.base

import android.annotation.SuppressLint
import android.app.ActivityOptions
import androidx.fragment.app.Fragment
import com.d9tilov.presentation.entities.Movie

open class BaseFragment: Fragment() {

    @SuppressLint("NewApi")
    protected fun navigateToMovieDetailsScreen(movie: Movie) {
        var activityOptions: ActivityOptions? = null

    }
}