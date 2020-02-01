package com.d9tilov.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.d9tilov.presentation.App
import com.d9tilov.presentation.R
import com.d9tilov.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var factory: MovieDetailsVMFactory
    private lateinit var detailsViewModel: MovieDetailsViewModel

    companion object {
        private const val EXTRA_ARGS_MOVIE_ID = "movie_id"
        val MOVIE_DETAILS_FRAGMENT_TAG = MovieDetailsFragment::class.java.name

        fun newInstance(movieId: Long): Fragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putLong(EXTRA_ARGS_MOVIE_ID, movieId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createDetailsComponent().inject(this)
        val movieId = arguments?.getLong(EXTRA_ARGS_MOVIE_ID) ?: -1
        factory.movieId = movieId
        initViewModel()

        if (savedInstanceState == null) {
            detailsViewModel.getMovieDetails()
        }
    }

    private fun initViewModel() {
        detailsViewModel =
            ViewModelProviders.of(this, factory).get(MovieDetailsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailsViewModel.viewState.observe(this as LifecycleOwner, Observer { viewState ->
            handleViewState(viewState)
        })
        detailsViewModel.errorState.observe(this as LifecycleOwner, Observer { throwable ->
            throwable?.let {
                Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleViewState(state: MovieDetailsViewState?) {
        if (state == null)
            return

        state.backdropUrl?.let {
            posterimage.load(it)
        }
        movie_title.text = state.title
        tagline.text = state.overview
        date_status.text = state.releaseDate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releaseDetailsComponent()
    }
}
