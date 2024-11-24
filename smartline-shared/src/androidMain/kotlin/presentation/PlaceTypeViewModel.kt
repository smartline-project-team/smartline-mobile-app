package presentation

import models.PlaceType

class PlaceTypeViewModel {
    fun getPlaceTypes(): List<PlaceType> = PlaceType.values().toList()
}
