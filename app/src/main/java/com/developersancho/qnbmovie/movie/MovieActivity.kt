package com.developersancho.qnbmovie.movie

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.amitshekhar.DebugDB
import com.developersancho.common.MIN_SEARCH_CHAR
import com.developersancho.common.MOVIE_DETAIL
import com.developersancho.common.extensions.clearSpaces
import com.developersancho.common.extensions.getMyColor
import com.developersancho.common.extensions.onChange
import com.developersancho.qnbmovie.R
import com.developersancho.qnbmovie.base.BaseViewBindingActivity
import com.developersancho.qnbmovie.databinding.ActivityMovieBinding
import com.developersancho.qnbmovie.moviedetail.MovieDetailActivity
import com.developersancho.qnbmovie.moviedetail.MovieDetailViewModel
import com.developersancho.remote.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : BaseViewBindingActivity<ActivityMovieBinding>(
    layoutId = R.layout.activity_movie,
    bind = ActivityMovieBinding::bind
) {
    private val viewModel by viewModel<MovieViewModel>()
    private val movieAdapter by lazy { MovieAdapter() }


    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun initView() {
        Log.d("Debug Database: ", DebugDB.getAddressLog())
        initAdapter()
        viewModel.loadMore(page = viewModel.pageNumber)
    }

    private fun initAdapter() {
        val glm = GridLayoutManager(this, 2)
        glm.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (movieAdapter.getItemViewType(position)) {
                    ViewType.MOVIE_VIEW.value -> 1
                    ViewType.LOAD_MORE_VIEW.value -> 2
                    else -> -1
                }
            }
        }
        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            layoutManager = glm
        }

        binding.swipeMovie.setColorSchemeColors(
            getMyColor(R.color.colorPrimary),
            getMyColor(R.color.colorAccent),
            getMyColor(R.color.colorPrimaryDark)
        )
    }

    override fun initListener() {
        binding.swipeMovie.setOnRefreshListener {
            viewModel.pageNumber = 1
            viewModel.loadMore(page = viewModel.pageNumber)
        }

        movieAdapter.onMovieClick = {
            openMovieDetailActivity(it)
        }

        movieAdapter.onLoadMore = {
            viewModel.loadMore(page = viewModel.pageNumber)
        }

        binding.btnSearch.setOnClickListener {

        }

        binding.etSearch.onChange {
            searchQuery(it)
        }
    }

    private fun searchQuery(keyword: String) {
        var searchText = keyword
        searchText = "%$searchText%"
        if (keyword.clearSpaces().length >= MIN_SEARCH_CHAR) {
            viewModel.isSearched = true
            viewModel.findMovieByTitle(searchText)
        } else if (keyword.clearSpaces().isEmpty()) {
            viewModel.getMovies()
        }
    }

    private fun openMovieDetailActivity(it: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_DETAIL, getMovieDetail(it))
        startActivity(intent)
    }

    private fun getMovieDetail(movie: Movie) = MovieDetailViewModel.MovieDetail().apply {
        title = movie.title
        overView = movie.overview
        popularity = movie.popularity
        voteAverage = movie.voteAverage
    }

    override fun observeUI() {
        super.observeUI()
        viewModel.movie.observe(this, Observer { movies ->
            if (!viewModel.isSearched) {
                viewModel.pageNumber++
            }

            movieAdapter.setData(movies)

            binding.swipeMovie.isRefreshing = false

            if (binding.etSearch.text.toString().clearSpaces().isEmpty()) {
                if (viewModel.isSearched)
                    viewModel.isSearched = false
            }

        })
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

}