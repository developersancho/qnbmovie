package com.developersancho.remote

import com.developersancho.remote.service.IMovieService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject

@RunWith(JUnit4::class)
class MovieServiceTest : BaseServiceTest() {

    private val service by inject<IMovieService>()

    @Test
    fun getAdvertisement() = runBlocking {
        val response = service.getMovieTopRated(
            apiKey = "11459cff1c1ce00e3202addab99f3a91",
            language = "en-us",
            page = 1
        )

        Assert.assertEquals(1, response.page)
    }
}