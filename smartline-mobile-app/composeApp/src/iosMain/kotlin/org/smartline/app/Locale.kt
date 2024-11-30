package org.smartline.app

actual val language: String
    get() = NSLocale.currentLocale.languageCode