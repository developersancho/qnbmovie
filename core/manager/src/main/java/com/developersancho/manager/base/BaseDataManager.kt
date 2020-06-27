package com.developersancho.manager.base

import com.developersancho.remote.helper.NetworkState
import com.developersancho.remote.helper.RemoteDataException

abstract class BaseDataManager {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): NetworkState<T> {
        return try {
            val response = call()
            NetworkState.Success(response)
        } catch (ex: Throwable) {
            NetworkState.Error(RemoteDataException(ex))
        }
    }

}