package com.allwin.haugiang.prediction.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.allwin.haugiang.R
import com.allwin.haugiang.common.base.BaseFragment
import com.allwin.haugiang.common.binding.viewBinding
import com.allwin.haugiang.common.extensions.convert
import com.allwin.haugiang.common.extensions.convertToString
import com.allwin.haugiang.common.extensions.writeLogs
import com.allwin.haugiang.common.state.UiEvent
import com.allwin.haugiang.databinding.FragmentPredictionsBinding
import com.allwin.haugiang.prediction.data.dto.PredictionModel
import com.allwin.haugiang.prediction.data.dto.map2D
import com.allwin.haugiang.prediction.ui.dialog.PredictionDialog
import com.allwin.haugiang.prediction.ui.utils.Segment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PredictionsFragment: BaseFragment(R.layout.fragment_predictions),
    PredictionController. Listener {

    private var datePickerDialog: DatePickerDialog? = null

    private val binding by viewBinding(FragmentPredictionsBinding::bind)

    private val controller by lazy {
        PredictionController(this)
    }

    private val viewModel by viewModels<PredictionsViewModel>()

    override fun setupViews() {
        with(binding){
            viewModel.getSelectedPrediction(Date().convert())
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.setController(controller)
            controller.requestModelBuild()
        }
    }

    override fun viewModelObservers() {
        viewModel.uiEvent.observe(viewLifecycleOwner){event ->
            when(event){
                is UiEvent.Loading -> {}
                is UiEvent.Error -> writeLogs(event.error.localizedMessage?:"")
                is PredictionEvent.PredictionError -> viewModel.savePrediction(event.prediction)
                is PredictionEvent.PredictionSucceed -> showPredictions(event.prediction)
                is PredictionEvent.PredictionToday -> controller.prediction = event.prediction
            }
        }
    }

    private fun showPredictions(prediction: PredictionModel) {
        var segment = ""
        val date = Date(prediction.id).convertToString()
        val predictions: List<String>

        when (controller.selectedSegment){
            Segment.MAX -> {
                segment = Segment.MAX.segmentModel.name ?: ""
                predictions = prediction.getConvertedMax3d()
            }
            Segment.MEGA -> {
                segment = Segment.MEGA.segmentModel.name ?: ""
                predictions = prediction.mega645.map2D()
            }
            Segment.POWER -> {
                segment = Segment.POWER.segmentModel.name ?: ""
                predictions = prediction.power655.map2D()
            }
            Segment.KENO -> {
                segment = Segment.KENO.segmentModel.name ?: ""
                predictions = prediction.keno.map2D()
            }
            else -> return
        }

        PredictionDialog(requireContext()).show(predictions, segment, date)
    }

    override fun onSegmentClick() {
        initDatePicker()
    }

    override val controllerContext: Context
        get() = requireContext()


    private fun initDatePicker() {
        val cldr = Calendar.getInstance()
        val day = cldr[Calendar.DAY_OF_MONTH]
        val month = cldr[Calendar.MONTH]
        val year = cldr[Calendar.YEAR]

        // date picker dialog
        datePickerDialog = DatePickerDialog(requireContext(), R.style.DatePickerTheme,
            { _, y, monthOfYear, dayOfMonth ->
                //
                cldr.set(y,monthOfYear,dayOfMonth)
                writeLogs(cldr.time.convertToString())
                viewModel.getSelectedPrediction(cldr.time)
            }, year, month, day
        )
        datePickerDialog!!.show()
        datePickerDialog!!.getButton(DatePickerDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.app_gold))
    }

}