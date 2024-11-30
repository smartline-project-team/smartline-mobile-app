package org.smartline.app.models

sealed class Language(val isoFormat : String) {
    data object English : Language("en")
    data object Russian : Language("ru")
    data object Kyrgyz : Language("ky")
}