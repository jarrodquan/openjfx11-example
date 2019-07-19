package com.jarrodquan.common

import java.net.URL

sealed class AppResource {
    companion object {
        fun load(path: String): URL? = this::class.java.classLoader.getResource(path)
    }
}