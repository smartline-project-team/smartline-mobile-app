package com.smartline.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform