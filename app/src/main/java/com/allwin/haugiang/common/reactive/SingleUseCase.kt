package com.allwin.haugiang.common.reactive

import com.allwin.haugiang.common.executor.ExecutionThread
import com.allwin.haugiang.common.executor.PostExecutionThread
import io.reactivex.Single

abstract class SingleUseCase<T, Param>(
    private val executionThread: ExecutionThread,
    private val postExecutionThread: PostExecutionThread
) {

    abstract fun buildObservable(param: Param): Single<T>

    fun execute(param: Param): Single<T> {
        return buildObservable(param)
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)
    }

}
