package com.d9tilov.presentation.ui.movies.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d9tilov.presentation.App
import com.d9tilov.presentation.R
import com.d9tilov.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : BaseFragment() {

    @Inject
    lateinit var factory: PopularMoviesVMFactory
    private lateinit var viewModel: MoviesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createMoviesComponent().inject(this)
        initViewModel()
        if (savedInstanceState == null) {
            viewModel.getMovies()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this as LifecycleOwner, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this as LifecycleOwner, Observer { throwable ->
            throwable?.let {
                Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override val layoutId = R.layout.fragment_movie_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = popular_movies_progress
        moviesAdapter = MoviesAdapter { movie, view ->
            navigateToMovieDetailsScreen(movie)
        }
        recyclerView = popular_movies_recyclerview
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = moviesAdapter
    }

    private fun handleViewState(state: PopularMoviesViewState) {
        progressBar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.movies?.let { moviesAdapter.addMovies(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releaseMoviesComponent()
    }

    companion object {
        val MOVIE_LIST_FRAGMENT_TAG = MovieListFragment::class.java.name
        fun newInstance() = MovieListFragment()
    }
}
