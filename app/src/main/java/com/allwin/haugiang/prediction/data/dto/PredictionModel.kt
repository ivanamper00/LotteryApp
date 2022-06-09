package com.allwin.haugiang.prediction.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.allwin.haugiang.app.data.remote.AppDatabase
import com.allwin.haugiang.common.extensions.getLongDay
import java.util.*

@Entity(tableName = AppDatabase.PREDICTIONS_TABLE)
data class PredictionModel(
    val mega645: List<Int>,
    val power655: List<Int>,
    val max3d: List<String>,
    val keno: List<Int>,
    @PrimaryKey(autoGenerate = false)
    var id: Long = Date().getLongDay()
){
    fun getConvertedMax3d(): List<String> {
        return max3d.map {
            it.convertTo3d()
        }
    }
}

fun List<Int>.map2D() : List<String>{
    return this.map {
        it.convertTo2d()
    }
}

fun Int.convertTo2d(): String{
    val numStr = this.toString()
    return when (numStr.length){
        1 -> "0$numStr"
        else -> numStr
    }
}

fun String.convertTo3d(): String{
    val numStr = this
    return when (numStr.length){
        1 -> "00$numStr"
        2 -> "0$numStr"
        else -> numStr
    }
}