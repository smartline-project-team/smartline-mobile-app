package org.smartline.app.localization

import java.util.Locale


actual fun changeLang(lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
}