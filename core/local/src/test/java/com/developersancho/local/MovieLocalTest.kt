package com.developersancho.local

import android.os.Build
import android.util.Log
import com.developersancho.local.dao.MovieDao
import com.developersancho.local.entity.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MovieLocalTest : BaseLocalTest() {

    private val dao by inject<MovieDao>()

    @Test
    fun insertFav() = runBlocking {

        dao.insert(MovieEntity(id = 1, title = "movie 1"))

        val advertList = dao.getMovies()

        Log.d("AdvertisementDaoTest", "size ------> ${advertList.size}")

        Assert.assertEquals(1, advertList.size)

    }

}