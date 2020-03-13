package com.daijun.sunflower.practice.data

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
class Converters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(time: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = time }
}