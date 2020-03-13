package com.daijun.sunflower.practice.viewmodels

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.daijun.sunflower.practice.data.Plant
import com.daijun.sunflower.practice.data.PlantRepository

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/13
 * @description
 */
class PlantListViewModel internal constructor(
    plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val plants: LiveData<List<Plant>> = getSavedGrowZoneNumber().switchMap {
        if (it == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, num)
    }

    fun clearGrowZoneNumber() {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE)
    }

    fun isFiltered() = getSavedGrowZoneNumber().value != NO_GROW_ZONE

    private fun getSavedGrowZoneNumber() =
        savedStateHandle.getLiveData(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE)

    companion object {
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }
}

class PlantListViewModelFactory(
    private val plantRepository: PlantRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return PlantListViewModel(plantRepository, handle) as T
    }

}