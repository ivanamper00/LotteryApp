package com.allwin.haugiang.prediction.data.repository

import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.data.remote.PredictionsDao
import com.allwin.haugiang.prediction.domain.repository.PredictionRepo
import io.reactivex.Single

class PredictionRepoImp(
    private val predictionsDao: PredictionsDao
): PredictionRepo {

    override fun getPredictionByID(id: Long): Single<PredictionModel> = predictionsDao.getPredictionByID(id)

    override fun savePrediction(param: PredictionModel): Single<Unit> = predictionsDao.savePrediction(param)

}