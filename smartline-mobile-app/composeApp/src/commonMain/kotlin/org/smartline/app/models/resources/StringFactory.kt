package org.smartline.app.models.resources

private const val RU_LANG = "ru"
private const val KG_LANG = "kg"

object StringFactory {
    fun createStrings(language: String): StringResources = when (language) {
        RU_LANG -> RuStringResources()
        KG_LANG -> KgStringResources()
        else -> EnStringResources()
    }
}