package com.allwin.haugiang.feeds.domain.repository

import com.allwin.haugiang.feeds.data.dto.response.FeedsModel
import io.reactivex.Single

interface FeedsRepo {

    fun getFeeds(): Single<FeedsModel>

}