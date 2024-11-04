package com.dj.kmmapp2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform