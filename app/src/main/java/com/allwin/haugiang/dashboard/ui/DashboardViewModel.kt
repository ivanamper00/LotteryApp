package com.allwin.haugiang.dashboard.ui

import com.allwin.haugiang.common.base.BaseViewModel
import com.allwin.haugiang.common.extensions.writeLogs
import com.allwin.haugiang.common.state.UiEvent
import com.allwin.haugiang.feeds.domain.interactor.GetFeeds
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getFeeds: GetFeeds
): BaseViewModel() {

    fun getFeeds(){
        getFeeds.execute(Unit)
            .doOnSubscribe { _uiEvent.value = UiEvent.Loading(true) }
            .subscribe(
                {
                    _uiEvent.value = UiEvent.Loading(false)
                    _uiEvent.value = DashboardEvent.Feeds(it)
                },
                {
                    _uiEvent.value = UiEvent.Loading(false)
                    writeLogs( "getFeeds" + it.localizedMessage ?:"")
                }
            ).addTo(disposables)
    }
}