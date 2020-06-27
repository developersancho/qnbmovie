package com.developersancho.remote.helper

sealed class NetworkState<out T> {
    class Success<out T>(val response: T) : NetworkState<T>()
    class Error(val exception: RemoteDataException) : NetworkState<Nothing>()
}