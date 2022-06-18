package com.allwin.haugiang.play.ui.item_model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.allwin.haugiang.R
import com.allwin.haugiang.databinding.ItemSegmentBinding
import com.allwin.haugiang.databinding.ItemSegmentPlayBinding
import com.allwin.haugiang.prediction.domain.model.SegmentModel

@EpoxyModelClass(
    layout = R.layout.item_segment_play
)
abstract class SegmentDetailsItemModel: EpoxyModelWithHolder<SegmentDetailsItemModel.Holder>() {

    @EpoxyAttribute
    var segment: SegmentModel? = null

    @EpoxyAttribute
    var onItemClick: (() -> Unit)? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder.binding){

            segmentImage.setBackgroundResource(segment?.imageResource ?: 0)

            root.setOnClickListener {
                onItemClick?.invoke()
            }

            descriptionSegment.text = segment?.details
            titleSegment.text = segment?.name
        }
    }

    class Holder: EpoxyHolder() {
        lateinit var binding: ItemSegmentPlayBinding
        override fun bindView(itemView: View) {
           binding = ItemSegmentPlayBinding.bind(itemView)
        }
    }
}