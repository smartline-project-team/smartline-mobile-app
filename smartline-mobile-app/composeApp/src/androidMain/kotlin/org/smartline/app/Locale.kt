package org.smartline.app

import java.util.*

actual val language: String
    get() = Locale.getDefault().language