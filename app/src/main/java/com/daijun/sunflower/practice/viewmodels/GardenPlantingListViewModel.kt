package com.daijun.sunflower.practice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daijun.sunflower.practice.data.GardenPlantingRepository
import com.daijun.sunflower.practice.data.PlantAndGardenPlantings

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> = gardenPlantingRepository.getPlantedGardens()
}

class GardenPlantingListViewModelFactory(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenPlantingListViewModel(gardenPlantingRepository) as T
    }
}