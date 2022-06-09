package com.allwin.haugiang.prediction.domain.repository

import com.allwin.haugiang.prediction.data.dto.PredictionModel
import io.reactivex.Single

interface PredictionRepo {

    fun getPredictionByID(id: Long): Single<PredictionModel>

    fun savePrediction(param: PredictionModel): Single<Unit>

}