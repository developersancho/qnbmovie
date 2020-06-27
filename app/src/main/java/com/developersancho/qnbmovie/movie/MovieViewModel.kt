package com.developersancho.qnbmovie.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developersancho.manager.IDataManager
import com.developersancho.qnbmovie.BuildConfig
import com.developersancho.qnbmovie.base.BaseViewModel
import com.developersancho.qnbmovie.base.IBasePresenter
import com.developersancho.remote.helper.NetworkState
import com.developersancho.remote.model.Movie
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(dataManager: IDataManager) : BaseViewModel<IBasePresenter>(dataManager) {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> get() = _movie

    fun loadMore(page: Int) = viewModelScope.launch {
        getPresenter()?.showLoading()
        dataManager.getMovieTopRated(
            apiKey = BuildConfig.API_KEY,
            language = "en-us",
            page = page
        ).collect { state ->
            when (state) {
                is NetworkState.Success -> {
                    getPresenter()?.hideLoading()
                    _movie.value = state.response.results
                }
                is NetworkState.Error -> {
                    getPresenter()?.hideLoading()
                }
            }
        }
    }
}