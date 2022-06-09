package com.allwin.haugiang.prediction.ui

import com.allwin.haugiang.common.base.BaseViewModel
import com.allwin.haugiang.common.extensions.convert
import com.allwin.haugiang.common.extensions.getLongDay
import com.allwin.haugiang.common.extensions.writeLogs
import com.allwin.haugiang.common.state.UiEvent
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.domain.interactor.GeneratePrediction
import com.allwin.haugiang.prediction.domain.interactor.GetPredictionByID
import com.allwin.haugiang.prediction.domain.interactor.SavePrediction
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PredictionsViewModel @Inject constructor(
    private val getPredictionByID: GetPredictionByID,
    private val savePrediction: SavePrediction,
    private val generatePrediction: GeneratePrediction
): BaseViewModel() {

    fun getSelectedPrediction(date: Date){
        val prediction = prepdiction()
        prediction.id = date.getLongDay()

        getPredictionByID.execute(prediction.id)
            .doOnSubscribe { _uiEvent.value = UiEvent.Loading(true) }
            .subscribe(
                {

                    _uiEvent.value = UiEvent.Loading(false)
                    _uiEvent.value = if(date == Date().convert())
                        PredictionEvent.PredictionToday(it)
                    else  PredictionEvent.PredictionSucceed(it)
                },
                {
                    _uiEvent.value = UiEvent.Loading(false)
                    _uiEvent.value = PredictionEvent.PredictionError(prediction)
                }
            ).addTo(disposables)
    }

    fun savePrediction(param: PredictionModel){
        savePrediction.execute(param)
            .subscribe(
                {
                    writeLogs("Prediction Saved! ${Gson().toJson(param)}")
                    _uiEvent.value = if(param.id == Date().time)
                        PredictionEvent.PredictionToday(param)
                    else PredictionEvent.PredictionSucceed(param)
                },
                {
                    _uiEvent.value = UiEvent.Error(it)
                }
            ).addTo(disposables)
    }

    fun prepdiction(): PredictionModel = generatePrediction.invoke()
}