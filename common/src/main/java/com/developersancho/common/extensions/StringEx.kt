package com.developersancho.common.extensions

fun String.clearSpaces(): String {
    return this.replace("\\s+".toRegex(), "")
}