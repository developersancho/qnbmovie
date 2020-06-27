package com.developersancho.common

import android.app.Activity
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Activity.toast(message: String) {
    runOnUiThread {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun Activity.toastLong(message: String) {
    runOnUiThread {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

fun Activity.getMyColor(colorResource: Int) = ContextCompat.getColor(this, colorResource)