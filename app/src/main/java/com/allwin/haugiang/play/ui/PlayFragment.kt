package com.allwin.haugiang.play.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.allwin.haugiang.R
import com.allwin.haugiang.common.base.BaseFragment
import com.allwin.haugiang.common.binding.viewBinding
import com.allwin.haugiang.common.extensions.convertToString
import com.allwin.haugiang.common.ui.view.LottoContainerView
import com.allwin.haugiang.databinding.FragmentPlayBinding
import com.allwin.haugiang.prediction.ui.dialog.PredictionDialog
import com.allwin.haugiang.prediction.ui.utils.Segment
import com.google.gson.Gson
import java.util.*

class PlayFragment: BaseFragment(R.layout.fragment_play),
    PlayController.Listener{

    private val binding by viewBinding(FragmentPlayBinding::bind)

    private val controller by lazy {
        PlayController(this)
    }

    override fun setupViews() {
        with(binding){
            epoxyRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            epoxyRecycler.setController(controller)
            controller.requestModelBuild()
        }
    }

    override fun viewModelObservers() {

    }

    override fun onSegmentClick(segment: Segment) {
        val directions = PlayFragmentDirections.actionPlayFragmentToSegmentPlayFragment(Gson().toJson(segment))
        navController.navigate(directions)
    }
}