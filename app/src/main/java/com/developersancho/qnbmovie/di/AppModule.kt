package com.developersancho.qnbmovie.di

import com.developersancho.local.di.localModule
import com.developersancho.manager.di.managerModule
import com.developersancho.qnbmovie.BuildConfig
import com.developersancho.remote.di.remoteModule

val appModule = listOf(
    remoteModule(
        baseUrl = BuildConfig.BASE_URL,
        isDebug = BuildConfig.DEBUG
    ),
    localModule(dbName = BuildConfig.DB_NAME),
    viewModelModule,
    managerModule
)