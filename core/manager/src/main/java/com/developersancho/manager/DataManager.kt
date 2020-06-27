package com.developersancho.manager

import com.developersancho.local.dao.MovieDao
import com.developersancho.local.entity.MovieEntity
import com.developersancho.manager.base.BaseDataManager
import com.developersancho.remote.helper.NetworkState
import com.developersancho.remote.model.MovieResponse
import com.developersancho.remote.service.IMovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataManager(
    private val service: IMovieService,
    private val dao: MovieDao
) : BaseDataManager(), IDataManager {

    override fun getMovieTopRated(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<NetworkState<MovieResponse>> = flow {
        emit(apiCall { service.getMovieTopRated(apiKey, language, page) })
    }

    override suspend fun insertMovie(entity: MovieEntity) {
        return dao.insert(entity)
    }

    override suspend fun insertMovies(entities: List<MovieEntity>) {
        return dao.insert(entities)
    }

    override suspend fun getMovies(): Flow<List<MovieEntity>> = flow {
        emit(dao.getMovies())
    }

}