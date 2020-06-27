package com.developersancho.qnbmovie.moviedetail

import android.os.Parcelable
import com.developersancho.manager.IDataManager
import com.developersancho.qnbmovie.base.BaseViewModel
import com.developersancho.qnbmovie.base.IBasePresenter
import kotlinx.android.parcel.Parcelize

class MovieDetailViewModel(dataManager: IDataManager) : BaseViewModel<IBasePresenter>(dataManager){

    @Parcelize
    class MovieDetail(
        var title: String? = null,
        var overView: String? = null,
        var popularity: Double? = null,
        var voteAverage: Double? = null
    ) : Parcelable


}