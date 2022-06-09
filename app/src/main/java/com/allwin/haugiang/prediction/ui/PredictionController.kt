package com.allwin.haugiang.prediction.ui

import android.content.Context
import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import com.allwin.haugiang.R
import com.allwin.haugiang.common.ui.item_model.TitleItemModel
import com.allwin.haugiang.common.ui.item_model.titleItem
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.data.dto.map2D
import com.allwin.haugiang.prediction.domain.model.SegmentModel
import com.allwin.haugiang.prediction.ui.item_model.segmentItem
import com.allwin.haugiang.prediction.ui.item_model.segmentWithPredictionItem
import com.allwin.haugiang.prediction.ui.utils.Segment

class PredictionController(
    private val listener: Listener
): EpoxyController() {

    var segmentList = Segment.values().toList()

    var selectedSegment: Segment? = null

    var prediction: PredictionModel? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        titleItem {
            id(TitleItemModel::class.simpleName)
            title(this@PredictionController.listener.controllerContext.getString(R.string.prediction))
        }

        segmentList.forEach {
            segmentWithPredictionItem {
                id(it.name)
                segment(it.segmentModel)
                onItemClick {
                    this@PredictionController.selectedSegment = it
                    this@PredictionController.listener.onSegmentClick()
                }
                predictions(
                    when(it){
                        Segment.MEGA -> this@PredictionController.prediction?.mega645?.map2D()
                        Segment.POWER -> this@PredictionController.prediction?.power655?.map2D()
                        Segment.MAX -> this@PredictionController.prediction?.getConvertedMax3d()
                        Segment.KENO -> this@PredictionController.prediction?.keno?.map2D()
                    }
                )
            }
        }
    }

    interface Listener {
        fun onSegmentClick()
        val controllerContext: Context
    }
}