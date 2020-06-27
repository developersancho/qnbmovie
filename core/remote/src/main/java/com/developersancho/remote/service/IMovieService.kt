package com.developersancho.remote.service

import com.developersancho.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieService {

    @GET("3/movie/top_rated")
    suspend fun getMovieTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

}