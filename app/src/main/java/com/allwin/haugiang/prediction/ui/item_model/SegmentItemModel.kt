package com.allwin.haugiang.prediction.ui.item_model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.allwin.haugiang.R
import com.allwin.haugiang.databinding.ItemSegmentBinding
import com.allwin.haugiang.prediction.domain.model.SegmentModel

@EpoxyModelClass(
    layout = R.layout.item_segment
)
abstract class SegmentItemModel: EpoxyModelWithHolder<SegmentItemModel.Holder>() {

    @EpoxyAttribute
    var segment: SegmentModel? = null

    @EpoxyAttribute
    var onItemClick: (() -> Unit)? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder.binding){

            segmentImage.setImageResource(segment?.imageResource ?: 0)

            root.setOnClickListener {
                onItemClick?.invoke()
            }
        }
    }

    class Holder: EpoxyHolder() {
        lateinit var binding: ItemSegmentBinding
        override fun bindView(itemView: View) {
           binding = ItemSegmentBinding.bind(itemView)
        }
    }
}