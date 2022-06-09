package com.allwin.haugiang.play.ui

import com.airbnb.epoxy.EpoxyController
import com.allwin.haugiang.common.ui.item_model.TitleItemModel
import com.allwin.haugiang.common.ui.item_model.titleItem
import com.allwin.haugiang.play.ui.item_model.segmentDetailsItem
import com.allwin.haugiang.prediction.ui.utils.Segment

class PlayController(private val listener: Listener): EpoxyController() {

    override fun buildModels() {

        titleItem {
            id(TitleItemModel::class.simpleName)
            title("Chơi")
        }

        Segment.values().forEach {
            segmentDetailsItem {
                id(it.name)
                segment(it.segmentModel)
                onItemClick { this@PlayController.listener.onSegmentClick(it) }
            }
        }
    }

    interface Listener{
        fun onSegmentClick(segment: Segment)
    }
}