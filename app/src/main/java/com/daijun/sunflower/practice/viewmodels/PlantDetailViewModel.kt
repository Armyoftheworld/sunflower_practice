package com.daijun.sunflower.practice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.daijun.sunflower.practice.data.GardenPlantingRepository
import com.daijun.sunflower.practice.data.PlantRepository
import kotlinx.coroutines.launch

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/9
 * @description
 */
class PlantDetailViewModel(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    val isPlanted = gardenPlantingRepository.isPlanted(plantId)

    val plant = plantRepository.getPlant(plantId)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }
}

class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T
    }
}

