package com.developersancho.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.developersancho.local.entity.MovieEntity

@Dao
interface MovieDao : BaseDao<MovieEntity> {

    @Query("DELETE FROM movie_tbl")
    suspend fun delete()

    @Query("SELECT * FROM movie_tbl")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_tbl WHERE title LIKE :movieTitle")
    suspend fun findMovieByTitle(movieTitle: String): List<MovieEntity>

}