package com.allwin.haugiang.feeds.data.remote

import com.allwin.haugiang.feeds.data.dto.response.FeedsModel
import io.reactivex.Single
import retrofit2.http.GET

interface FeedService {

    @GET("blog/feed/")
    fun getFeeds(): Single<FeedsModel>
}