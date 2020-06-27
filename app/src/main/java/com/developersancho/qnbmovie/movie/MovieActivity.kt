package com.developersancho.qnbmovie.movie

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.developersancho.common.MOVIE_DETAIL
import com.developersancho.common.getMyColor
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
    private var pageNumber = 1
    private var refresh = false

    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun initView() {
        initAdapter()
        viewModel.loadMore(page = pageNumber)
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
            refresh = true
            pageNumber = 1
            viewModel.loadMore(page = pageNumber)
        }

        movieAdapter.onMovieClick = {
            openMovieDetailActivity(it)
        }

        movieAdapter.onLoadMore = {
            viewModel.loadMore(page = pageNumber)
        }

        binding.btnSearch.setOnClickListener {
            // TODO("minimum 3 karakter girmeden çalışmamalı
            //  3 karakter ve sonrası için arama işlemi yapılacak
            //  title üzerinden aranan filmler listelenmeli
            //  locale kaydetmek gerekir mi?")

            // TODO("-	Bu sayfanın en üstünde bir search bar olmalı.
            //  Bu search bar minimum 3 karakter girmeden çalışmamalı.
            //  Minimum karakter sayısından fazla karakter girildiğinde api servisleri kullanılmadan
            //  o ana kadar lokale çekilmiş dayı filtremeli sadece aranan filmleri göstermeli.")
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
            pageNumber++
            movieAdapter.setData(movies, refresh)
            refresh = false
            binding.swipeMovie.isRefreshing = refresh
        })
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

}