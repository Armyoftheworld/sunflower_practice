package com.daijun.sunflower.practice.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description This class captures the relationship between a [Plant] and a user's [GardenPlanting],
 * which is used by Room to fetch the related entities.
 */
data class PlantAndGardenPlantings(
    @Embedded val plant: Plant,

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    val gardenPlantings: List<GardenPlanting> = emptyList()
)