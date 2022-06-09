package com.allwin.haugiang.prediction.domain.interactor

import com.allwin.haugiang.common.executor.ExecutionThread
import com.allwin.haugiang.common.executor.PostExecutionThread
import com.allwin.haugiang.common.reactive.SingleUseCase
import com.allwin.haugiang.common.thread.ExecutorThread
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.domain.repository.PredictionRepo
import io.reactivex.Single
import javax.inject.Inject

class GetPredictionByID @Inject constructor(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread,
    private val predictionRepo: PredictionRepo
): SingleUseCase<PredictionModel, Long>(
    executionThread, postExecutionThread
) {
    override fun buildObservable(param: Long): Single<PredictionModel> = predictionRepo.getPredictionByID(param)
}