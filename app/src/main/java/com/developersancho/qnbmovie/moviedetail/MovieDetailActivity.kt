package com.developersancho.qnbmovie.moviedetail

import android.os.Parcelable
import com.developersancho.common.MOVIE_DETAIL
import com.developersancho.qnbmovie.R
import com.developersancho.qnbmovie.base.BaseViewBindingActivity
import com.developersancho.qnbmovie.databinding.ActivityMovieDetailBinding
import kotlinx.android.parcel.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : BaseViewBindingActivity<ActivityMovieDetailBinding>(
    layoutId = R.layout.activity_movie_detail,
    bind = ActivityMovieDetailBinding::bind
) {
    private val viewModel by viewModel<MovieDetailViewModel>()


    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun initView() {
        val movieDetail = intent.getParcelableExtra<MovieDetailViewModel.MovieDetail>(MOVIE_DETAIL)
        binding.tvTitle.text = movieDetail?.title
        binding.tvOverView.text = movieDetail?.overView
        binding.tvPopularity.text = "${movieDetail?.popularity}"
        binding.tvVoteAverage.text = "${movieDetail?.voteAverage}"
    }

    override fun initListener() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


}