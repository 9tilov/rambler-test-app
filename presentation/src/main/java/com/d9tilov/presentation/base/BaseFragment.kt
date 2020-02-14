package com.d9tilov.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.d9tilov.presentation.R
import com.d9tilov.presentation.entities.Movie
import com.d9tilov.presentation.ui.detail.MovieDetailsFragment

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }


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
