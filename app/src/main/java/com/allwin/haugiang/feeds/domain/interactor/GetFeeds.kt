package com.allwin.haugiang.feeds.domain.interactor

import com.allwin.haugiang.common.executor.ExecutionThread
import com.allwin.haugiang.common.executor.PostExecutionThread
import com.allwin.haugiang.common.reactive.SingleUseCase
import com.allwin.haugiang.feeds.data.dto.response.FeedsModel
import com.allwin.haugiang.feeds.domain.repository.FeedsRepo
import io.reactivex.Single
import javax.inject.Inject

class GetFeeds @Inject constructor(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread,
    private val feedsRepo: FeedsRepo
): SingleUseCase<FeedsModel, Unit>(
    executionThread, postExecutionThread
) {
    override fun buildObservable(param: Unit): Single<FeedsModel> = feedsRepo.getFeeds()
}