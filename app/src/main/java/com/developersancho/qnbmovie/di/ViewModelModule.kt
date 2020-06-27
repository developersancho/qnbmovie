package com.developersancho.qnbmovie.di

import com.developersancho.qnbmovie.movie.MovieViewModel
import com.developersancho.qnbmovie.moviedetail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}