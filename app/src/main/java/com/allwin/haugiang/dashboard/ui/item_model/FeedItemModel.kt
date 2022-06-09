package com.allwin.haugiang.dashboard.ui.item_model

import android.view.View
import androidx.core.text.HtmlCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.allwin.haugiang.R
import com.allwin.haugiang.common.extensions.loadUrl
import com.allwin.haugiang.databinding.ItemFeedsBinding
import com.allwin.haugiang.feeds.data.dto.response.FeedsModel

@EpoxyModelClass(
    layout = R.layout.item_feeds
)
abstract class FeedItemModel: EpoxyModelWithHolder<FeedItemModel.Holder>() {

    @EpoxyAttribute
    var onItemClick: (() -> Unit)? = null

    @EpoxyAttribute
    var item: FeedsModel.Channel.Item? = null

    @EpoxyAttribute
    var source: String? = ""

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder.binding){
            root.setOnClickListener {
                onItemClick?.invoke()
            }

            contentImage.loadUrl(item?.content?.url ?: "")
            contentDesciption.text = HtmlCompat.fromHtml(item?.description ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
            publishedDate.text = "${item?.pubDate}\n$source"
        }
    }

    class Holder: EpoxyHolder() {
        lateinit var binding: ItemFeedsBinding
        override fun bindView(itemView: View) {
            binding = ItemFeedsBinding.bind(itemView)
        }
    }
}