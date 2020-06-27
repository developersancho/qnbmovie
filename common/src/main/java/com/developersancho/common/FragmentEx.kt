package com.developersancho.common

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


fun Fragment.toast(message: String) {
    activity?.runOnUiThread {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toastLong(message: String) {
    activity?.runOnUiThread {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

fun Fragment.getColor(colorResource: Int) = ContextCompat.getColor(context!!, colorResource)