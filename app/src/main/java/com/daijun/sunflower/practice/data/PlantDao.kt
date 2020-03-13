package com.daijun.sunflower.practice.data

import android.graphics.Paint
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
@Dao
interface PlantDao {

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): LiveData<List<Plant>>

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<Plant>>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String): LiveData<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Plant>)
}