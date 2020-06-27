package com.developersancho.manager

import android.os.Build
import android.util.Log
import com.developersancho.local.dao.MovieDao
import com.developersancho.local.entity.MovieEntity
import com.developersancho.remote.helper.NetworkState
import com.developersancho.remote.service.IMovieService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ManagerTest : BaseManagerTest() {

    private val service by inject<IMovieService>()
    private val movieDao by inject<MovieDao>()


    @Before
    fun setUpData() {
        dataManager = DataManager(service, movieDao)
    }

    @Test
    fun `Get Movie Top Rated`() = runBlocking {
        dataManager.getMovieTopRated(
            apiKey = "11459cff1c1ce00e3202addab99f3a91",
            language = "en-us",
            page = 1
        ).collect {
            when (it) {
                is NetworkState.Success -> {
                    Assert.assertEquals(1, it.response.page)
                }

                is NetworkState.Error -> {
                    Log.d("ManagerTest", "NetworkState.Error : ${it.exception.message}")
                }
            }
        }
    }

    @Test
    fun `Get Movie Top Rated From Database`() = runBlocking {
        val list: MutableList<MovieEntity> = mutableListOf()
        list.add(MovieEntity(id = 1, title = "movie 1"))
        list.add(MovieEntity(id = 2, title = "movie 2"))
        list.add(MovieEntity(id = 3, title = "movie 3"))

        dataManager.insertMovies(list)

        dataManager.getMovies().collect {
            Assert.assertEquals(1, it.size)
        }
    }

}