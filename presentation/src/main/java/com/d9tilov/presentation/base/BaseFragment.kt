package com.d9tilov.presentation.base

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.d9tilov.presentation.R
import com.d9tilov.presentation.entities.Movie
import com.d9tilov.presentation.ui.detail.MovieDetailsFragment

open class BaseFragment : Fragment() {

    @SuppressLint("NewApi")
    protected fun navigateToMovieDetailsScreen(movie: Movie) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, MovieDetailsFragment.newInstance(movie.id),
                MovieDetailsFragment.MOVIE_DETAILS_FRAGMENT_TAG
            )
            .addToBackStack(null)
            .commit()
    }
}
