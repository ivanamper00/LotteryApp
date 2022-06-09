package com.allwin.haugiang.dashboard.ui

import com.allwin.haugiang.common.state.UiEvent
import com.allwin.haugiang.feeds.data.dto.response.FeedsModel

sealed class DashboardEvent: UiEvent() {
    data class Feeds(val data: FeedsModel): UiEvent()
}