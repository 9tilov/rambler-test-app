package com.d9tilov.presentation.ui.movies.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d9tilov.presentation.R
import com.d9tilov.presentation.ui.movies.fragments.MovieListFragment
import com.d9tilov.presentation.ui.movies.fragments.MovieListFragment.Companion.MOVIE_LIST_FRAGMENT_TAG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance(), MOVIE_LIST_FRAGMENT_TAG)
                .commit()
        }
    }
}
