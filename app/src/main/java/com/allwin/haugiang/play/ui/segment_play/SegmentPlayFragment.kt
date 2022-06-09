package com.allwin.haugiang.play.ui.segment_play

import android.widget.PopupMenu
import androidx.navigation.fragment.navArgs
import com.allwin.haugiang.R
import com.allwin.haugiang.common.base.BaseFragment
import com.allwin.haugiang.common.binding.viewBinding
import com.allwin.haugiang.common.extensions.convertToString
import com.allwin.haugiang.common.ui.view.LottoContainerView
import com.allwin.haugiang.databinding.FragmentPlayBinding
import com.allwin.haugiang.databinding.FragmentSegmentPlayBinding
import com.allwin.haugiang.prediction.domain.model.SegmentModel
import com.allwin.haugiang.prediction.ui.dialog.PredictionDialog
import com.allwin.haugiang.prediction.ui.utils.Segment
import com.google.gson.Gson
import java.util.*

class SegmentPlayFragment: BaseFragment(R.layout.fragment_segment_play) {

    private val binding by viewBinding(FragmentSegmentPlayBinding::bind)

    private val args : SegmentPlayFragmentArgs by navArgs()

    private val segment by lazy {
        Gson().fromJson(args.segment, Segment::class.java)
    }

    override fun setupViews() {

        binding.headerTitle.text = segment.segmentModel.name
        binding.headerSub.text = getString(R.string.play_segment_sub)

        binding.lottoView.apply {
            when(segment){
                Segment.MEGA -> {
                    minNumber = 1
                    maxNumber = 45
                    numberOfDraw = 6
                    repeatableDraw = false
                }
                Segment.POWER -> {
                    minNumber = 1
                    maxNumber = 55
                    numberOfDraw = 6
                    repeatableDraw = false
                }
                Segment.MAX -> {
                    minNumber = 0
                    maxNumber = 9
                    numberOfDraw = 3
                    repeatableDraw = true
                }
                Segment.KENO -> {
                    minNumber = 0
                    maxNumber = 81
                    numberOfDraw = 20
                    repeatableDraw = true
                }
                else -> {
                    snackToast("Error loading the game. Please try again")
                    onBackPressed()
                }
            }
        }

        binding.lottoView.listener = object : LottoContainerView.Listener {
            override fun onNewItemSelected(ballNumber: String, ballList: List<String>) { }
            override fun onDone(ballList: List<String>) {
                PredictionDialog(requireContext()).apply {
                    predictionText = "Rút thăm chiến thắng của bạn cho trò chơi này!\nNhiều tiền thắng hơn sẽ đến."
                    setOnDismissListener{
                        this@SegmentPlayFragment.onBackPressed()
                    }
                }.show(ballList, segment.segmentModel.name ?: "" , Date().convertToString())
            }
        }

        binding.ballGenerator.setOnClickListener {
            binding.lottoView.autoPickBall()
        }
    }

    override fun viewModelObservers() {

    }
}