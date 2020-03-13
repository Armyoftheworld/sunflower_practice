package com.daijun.sunflower.practice.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.daijun.sunflower.practice.data.AppDatabase
import com.daijun.sunflower.practice.data.GardenPlantingRepository
import com.daijun.sunflower.practice.data.PlantRepository
import com.daijun.sunflower.practice.viewmodels.GardenPlantingListViewModelFactory
import com.daijun.sunflower.practice.viewmodels.PlantDetailViewModelFactory
import com.daijun.sunflower.practice.viewmodels.PlantListViewModelFactory

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context).gardenPlantingDao())
    }

    fun provideGardenPlantingListViewModelFactory(context: Context):
            GardenPlantingListViewModelFactory =
        GardenPlantingListViewModelFactory(getGardenPlantingRepository(context))

    fun providePlantDetailViewModelFactory(context: Context, plantId: String) =
        PlantDetailViewModelFactory(
            getPlantRepository(context),
            getGardenPlantingRepository(context),
            plantId
        )

    fun providePlantListViewModelFactory(fragment: Fragment) =
        PlantListViewModelFactory(getPlantRepository(fragment.requireContext()), fragment)
}