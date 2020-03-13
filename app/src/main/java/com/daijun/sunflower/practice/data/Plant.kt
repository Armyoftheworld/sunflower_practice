package com.daijun.sunflower.practice.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
@Entity(tableName = "plants")
data class Plant(
    @[PrimaryKey ColumnInfo(name = "id")] val plantId: String,
    val name: String, val description: String, val growZoneNumber: Int,
    // how often the plant should be watered, in days
    val wateringInterval: Int = 7,
    val imageUrl: String
) {

    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }
}