package com.allwin.haugiang.common.ui.item_model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.allwin.haugiang.R
import com.allwin.haugiang.common.extensions.getNumber
import com.allwin.haugiang.databinding.ItemTitleBinding

@EpoxyModelClass(
    layout = R.layout.item_title
)
abstract class TitleItemModel: EpoxyModelWithHolder<TitleItemModel.Holder>(){

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder.binding){
            headerTitle.text = title
            cardEnd.visibility = View.INVISIBLE
            cardStart.visibility = View.INVISIBLE
        }
    }

    class Holder: EpoxyHolder() {
        lateinit var binding: ItemTitleBinding
        override fun bindView(itemView: View) {
            binding = ItemTitleBinding.bind(itemView)
        }
    }

}