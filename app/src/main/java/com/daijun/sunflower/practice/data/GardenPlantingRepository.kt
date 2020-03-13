package com.daijun.sunflower.practice.data

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
class GardenPlantingRepository private constructor(private val gardenPlantingDao: GardenPlantingDao) {

    suspend fun createGardenPlanting(plantId: String) {
        val gardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }

    suspend fun deleteGardenPlanting(gardenPlanting: GardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    fun isPlanted(plantId: String) = gardenPlantingDao.isPlanted(plantId)

    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()

    companion object {
        @Volatile
        private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao): GardenPlantingRepository {
            return instance ?: synchronized(this) {
                instance ?: GardenPlantingRepository(gardenPlantingDao).also { instance = it }
            }
        }
    }
}