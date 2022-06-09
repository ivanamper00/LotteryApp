package com.allwin.haugiang.prediction.domain.interactor

import com.allwin.haugiang.prediction.data.dto.PredictionModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GeneratePrediction @Inject constructor() {

    private val megaList: List<Int>
        get() = (1..45).shuffled().toList()

    private val powerList: List<Int>
        get() = (1..55).shuffled().toList()

    private val maxList: List<String>
        get() = (0..999).shuffled().map {
            it.toString()
        }.toList()

    private val kenoList: List<Int>
        get(){
            val list = mutableListOf<Int>()
            (1..20).forEach{ _ ->
                list.add((0..81).shuffled()[0])
            }
            return list
        }

    operator fun invoke(): PredictionModel {
        return PredictionModel(
            mega645 = megaList.take(6),
            power655 = powerList.take(6),
            max3d = maxList.take(3),
            keno = kenoList.take(20)
        )
    }

}