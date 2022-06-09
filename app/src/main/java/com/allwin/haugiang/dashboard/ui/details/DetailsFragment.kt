package com.allwin.haugiang.dashboard.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import com.allwin.haugiang.R
import com.allwin.haugiang.common.base.BaseFragment
import com.allwin.haugiang.common.binding.viewBinding
import com.allwin.haugiang.common.extensions.loadUrl
import com.allwin.haugiang.databinding.FragmentDetailsBinding
import com.allwin.haugiang.feeds.data.dto.response.FeedsModel
import com.google.gson.Gson


class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    private val args: DetailsFragmentArgs by navArgs()

    override fun setupViews() {
        with(binding){
            val item = Gson().fromJson(args.item, FeedsModel.Channel.Item::class.java)
            headerImage.loadUrl(item?.content?.url ?: "")
            content.text = HtmlCompat.fromHtml(item?.contentEncoded?:"", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun viewModelObservers() {
    }

}