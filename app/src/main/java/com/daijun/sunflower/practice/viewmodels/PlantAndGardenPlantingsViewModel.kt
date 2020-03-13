package com.daijun.sunflower.practice.viewmodels

import com.daijun.sunflower.practice.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
class PlantAndGardenPlantingsViewModel(plants: PlantAndGardenPlantings) {
    private val plant = checkNotNull(plants.plant)
    private val gardenPlanting = plants.gardenPlantings[0]

    val waterDateString = dateFormat.format(gardenPlanting.lastWateringDate.time)

    val wateringInterval
        get() = plant.wateringInterval

    val imageUrl
        get() = plant.imageUrl

    val plantName
        get() = plant.name

    val plantDateString = dateFormat.format(gardenPlanting.plantDate.time)

    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    }
}