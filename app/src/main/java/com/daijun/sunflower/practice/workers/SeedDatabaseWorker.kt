package com.daijun.sunflower.practice.workers

import android.content.Context
import android.graphics.Paint
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.daijun.sunflower.practice.data.AppDatabase
import com.daijun.sunflower.practice.data.Plant
import com.daijun.sunflower.practice.utilities.PLANT_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope

/**
 * @author Army
 * @version V_1.0.0
 * @date 2020/3/8
 * @description
 */
class SeedDatabaseWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use {
                JsonReader(it.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
                    AppDatabase.getInstance(applicationContext).plantDao().insertAll(plantList)
                    Result.success()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}