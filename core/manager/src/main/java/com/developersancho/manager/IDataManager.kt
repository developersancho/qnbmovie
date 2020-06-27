package com.developersancho.manager

import com.developersancho.local.entity.MovieEntity
import com.developersancho.remote.helper.NetworkState
import com.developersancho.remote.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface IDataManager {

    fun getMovieTopRated(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<NetworkState<MovieResponse>>

    suspend fun insertMovie(entity: MovieEntity)

    suspend fun insertMovies(entities: List<MovieEntity>)

    suspend fun getMovies(): Flow<List<MovieEntity>>

}