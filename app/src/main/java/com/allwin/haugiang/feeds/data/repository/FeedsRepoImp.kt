package com.allwin.haugiang.feeds.data.repository

import com.allwin.haugiang.feeds.data.dto.response.FeedsModel
import com.allwin.haugiang.feeds.data.remote.FeedService
import com.allwin.haugiang.feeds.domain.repository.FeedsRepo
import io.reactivex.Single

class FeedsRepoImp(
    private val feedService: FeedService
): FeedsRepo {
    override fun getFeeds(): Single<FeedsModel> = feedService.getFeeds()
}