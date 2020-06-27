package com.developersancho.qnbmovie.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.wada811.viewbinding.viewBinding

abstract class BaseViewBindingActivity<T : ViewBinding>(
    @LayoutRes layoutId: Int,
    bind: (View) -> T
) : AppCompatActivity(layoutId),
    IBasePresenter {

    protected val binding by viewBinding(bind)

    protected abstract fun initPresenter()

    protected abstract fun initView()

    protected abstract fun initListener()

    open fun observeUI() {}

    open fun initLanguage() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        initLanguage()
        observeUI()
        initView()
        initListener()
    }

    protected fun showSnackbarWithAction(view: View, message: String, block: () -> Unit) {
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Retry") {
            block()
        }.show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }


}