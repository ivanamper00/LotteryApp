package com.allwin.haugiang.dashboard.ui

import com.airbnb.epoxy.EpoxyController
import com.allwin.haugiang.dashboard.ui.item_model.feedItem
import com.allwin.haugiang.feeds.data.dto.response.FeedsModel

class DashboardController(private val listener: Listener) : EpoxyController() {

    var data: FeedsModel? = null
    set(value) {
        field = value
        requestModelBuild()
    }

    override fun buildModels() {
        data?.channel?.item?.forEachIndexed { index, item ->
            feedItem {
                id(index)
                item(item)
                source(this@DashboardController.data?.channel?.title ?: "")
                onItemClick {
                    this@DashboardController.listener.onFeedsClick(item)
                }
            }
        }
    }

    interface Listener {
        fun onFeedsClick(item: FeedsModel.Channel.Item)
    }
}