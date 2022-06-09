package com.allwin.haugiang.common.state

open class UiEvent {
    data class Loading(val isLoading: Boolean): UiEvent()
    data class Error(val error: Throwable): UiEvent()
    object Success: UiEvent()
}
