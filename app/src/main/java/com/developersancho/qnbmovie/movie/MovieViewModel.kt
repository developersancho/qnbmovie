package com.developersancho.qnbmovie.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developersancho.local.entity.MovieEntity
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

    var pageNumber = 1
    var isSearched = false

    fun loadMore(page: Int) = viewModelScope.launch {
        pageNumber = page
        getPresenter()?.showLoading()
        dataManager.getMovieTopRated(
            apiKey = BuildConfig.API_KEY,
            language = "en-us",
            page = page
        ).collect { state ->
            when (state) {
                is NetworkState.Success -> {
                    getPresenter()?.hideLoading()
                    state.response.results?.let {
                        insertMovies(it)
                    }
                }
                is NetworkState.Error -> {
                    getPresenter()?.hideLoading()
                }
            }
        }
    }

    fun getMovies() = viewModelScope.launch {
        dataManager.getMovies().collect {
            _movie.value = getMovies(it)
        }
    }

    private fun insertMovies(results: List<Movie>) = viewModelScope.launch {
        if (pageNumber == 1) {
            dataManager.deleteMovies()
            dataManager.insertMovies(getMoviesEntity(results))
            getMovies()
        } else {
            dataManager.insertMovies(getMoviesEntity(results))
            getMovies()
        }
    }

    private fun getMoviesEntity(movies: List<Movie>): List<MovieEntity> {
        return movies.map { getMovieEntity(it) }
    }

    private fun getMovies(movieEntity: List<MovieEntity>): List<Movie> {
        return movieEntity.map { getMovie(it) }
    }

    private fun getMovieEntity(movie: Movie) = MovieEntity(
        id = movie.id,
        title = movie.title,
        adult = movie.adult,
        backdrop_path = movie.backdropPath,
        genre_ids = movie.genreIds,
        original_language = movie.originalLanguage,
        original_title = movie.originalTitle,
        overview = movie.overview,
        popularity = movie.popularity,
        poster_path = movie.posterPath,
        release_date = movie.releaseDate,
        video = movie.video,
        vote_average = movie.voteAverage,
        vote_count = movie.voteCount
    )

    private fun getMovie(entity: MovieEntity) = Movie(
        id = entity.id,
        title = entity.title,
        adult = entity.adult,
        backdropPath = entity.backdrop_path,
        genreIds = entity.genre_ids,
        originalLanguage = entity.original_language,
        originalTitle = entity.original_title,
        overview = entity.overview,
        popularity = entity.popularity,
        posterPath = entity.poster_path,
        releaseDate = entity.release_date,
        video = entity.video,
        voteAverage = entity.vote_average,
        voteCount = entity.vote_count
    )

    fun findMovieByTitle(title: String) = viewModelScope.launch {
        getPresenter()?.showLoading()
        dataManager.findMovieByTitle(movieTitle = title).collect {
            getPresenter()?.hideLoading()
            _movie.value = getMovies(it)
        }
    }
}