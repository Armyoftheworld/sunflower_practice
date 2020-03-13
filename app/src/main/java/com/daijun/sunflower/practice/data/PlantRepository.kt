package com.daijun.sunflower.practice.data

import android.content.Context

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
class PlantRepository private constructor(private val plantDao: PlantDao) {
    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)

    companion object {
        @Volatile
        private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao) = instance ?: synchronized(this) {
            instance ?: PlantRepository(plantDao).also { instance = it }
        }
    }
}