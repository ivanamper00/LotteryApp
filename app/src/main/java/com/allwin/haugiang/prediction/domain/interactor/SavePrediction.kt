package com.allwin.haugiang.prediction.domain.interactor

import com.allwin.haugiang.common.executor.ExecutionThread
import com.allwin.haugiang.common.executor.PostExecutionThread
import com.allwin.haugiang.common.reactive.SingleUseCase
import com.allwin.haugiang.common.thread.ExecutorThread
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.domain.repository.PredictionRepo
import io.reactivex.Single
import javax.inject.Inject

class SavePrediction @Inject constructor(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread,
    private val predictionRepo: PredictionRepo
): SingleUseCase<Unit, PredictionModel>(
    executionThread, postExecutionThread
) {
    override fun buildObservable(param: PredictionModel): Single<Unit> = predictionRepo.savePrediction(param)
}