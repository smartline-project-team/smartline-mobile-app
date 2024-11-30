package org.smartline.app.localization

actual fun changeLang(lang: String) {
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(lang),”AppleLanguages”)
}