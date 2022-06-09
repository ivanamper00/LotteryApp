package com.allwin.haugiang.prediction.ui

import com.allwin.haugiang.common.state.UiEvent
import com.allwin.haugiang.prediction.data.dto.PredictionModel

sealed class PredictionEvent: UiEvent() {
    data class PredictionSucceed(val prediction: PredictionModel): UiEvent()
    data class PredictionError(val prediction: PredictionModel): UiEvent()
    data class PredictionToday(val prediction: PredictionModel): UiEvent()
}
