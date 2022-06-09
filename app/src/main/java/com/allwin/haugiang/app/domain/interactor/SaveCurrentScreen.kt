package com.allwin.haugiang.app.domain.interactor

import com.allwin.haugiang.app.data.dto.CurrentScreenModel
import com.allwin.haugiang.app.domain.repository.ScreensRepo
import com.allwin.haugiang.common.executor.ExecutionThread
import com.allwin.haugiang.common.executor.PostExecutionThread
import com.allwin.haugiang.common.reactive.SingleUseCase
import com.allwin.haugiang.common.thread.ExecutorThread
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.domain.repository.PredictionRepo
import io.reactivex.Single
import javax.inject.Inject

class SaveCurrentScreen @Inject constructor(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread,
    private val screenRepo: ScreensRepo
): SingleUseCase<Unit, CurrentScreenModel>(
    executionThread, postExecutionThread
) {
    override fun buildObservable(param: CurrentScreenModel): Single<Unit> = screenRepo.saveCurrentScreen(param)
}