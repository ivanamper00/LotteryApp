package com.allwin.haugiang.app.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.allwin.haugiang.app.data.dto.CurrentScreenModel
import com.allwin.haugiang.app.data.dto.ScreenParams
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.data.remote.PredictionsDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities =
    [
        PredictionModel::class,
        CurrentScreenModel::class
    ],
    version = 1
)
@TypeConverters(DatabaseTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val predictionsDao: PredictionsDao
    abstract val screensDao: ScreensDao

    companion object{
        const val DATABASE_NAME = "lottery_db"
        const val PREDICTIONS_TABLE = "prediction_table"
        const val SCREENS_TABLE = "screens_table"
    }
}

class DatabaseTypeConverter {
    @TypeConverter
    fun fromScreenParamList(value: List<ScreenParams>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromString(value: String): List<ScreenParams>? {
        val listType = object : TypeToken<List<ScreenParams>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToIntList(value: String): List<Int> {
        val listType = object : TypeToken<List<Int>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntListToString(value: List<Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringListToString(value: List<String>): String {
        return Gson().toJson(value)
    }
}