package org.smartline.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform