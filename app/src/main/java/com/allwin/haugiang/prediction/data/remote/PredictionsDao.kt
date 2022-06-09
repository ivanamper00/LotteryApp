package com.allwin.haugiang.prediction.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.allwin.haugiang.app.data.remote.AppDatabase
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import io.reactivex.Single

@Dao
interface PredictionsDao {

    @Query("SELECT * FROM ${AppDatabase.PREDICTIONS_TABLE} WHERE `id` == :id")
    fun getPredictionByID(id: Long): Single<PredictionModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePrediction(param: PredictionModel): Single<Unit>

}
