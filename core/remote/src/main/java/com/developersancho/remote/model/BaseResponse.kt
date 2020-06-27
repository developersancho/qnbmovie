package com.developersancho.remote.model

import com.google.gson.annotations.SerializedName

open class BaseResponse<out T>(

    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0,

    @SerializedName("total_results")
    val totalResults: Int = 0,

    @SerializedName("results")
    val results: T? = null

)