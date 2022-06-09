package com.allwin.haugiang.prediction.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.allwin.haugiang.R
import com.allwin.haugiang.common.extensions.convert
import com.allwin.haugiang.common.extensions.toDate
import com.allwin.haugiang.common.extensions.writeLogs
import com.allwin.haugiang.databinding.DialogPredictionDrawBinding
import com.google.gson.Gson
import java.util.*

class PredictionDialog(
    context: Context
): Dialog(context) {

    lateinit var binding: DialogPredictionDrawBinding

    private var predictions: List<String>? = null

    private var title: String? = ""

    private var date: String? = null

    private val dateToday = Date().convert()

    var predictionText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogPredictionDrawBinding.inflate(
            LayoutInflater.from(context), null, false
        )
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        with(binding){

            binding.drawTitle.cardEnd.visibility = View.INVISIBLE
            binding.drawTitle.cardStart.visibility = View.INVISIBLE

            writeLogs(Gson().toJson(predictions))
            drawTitle.headerTitle.text = title

            predictionSub.text = predictionText.ifEmpty {
                String.format(
                    context.getString(R.string.format_draw),
                    if(dateToday == date?.toDate()) " today" else if (Date().convert().after(date?.toDate())) " last" else " on",
                    if(dateToday == date?.toDate()) "" else " $date",
                    " is"
                )
            }

            when (predictions?.size) {
                6 -> {
                    val container = generateLinearLayoutHorizontal()
                    container.weightSum = 6F
                    predictions?.forEach {
                        container.addView(generateBall(it))
                    }
                    drawResult.addView(container)
                }
                3 -> {
                    val container0 = generateLinearLayoutHorizontal()
                    predictions?.forEachIndexed { index, it ->
                        if(it.length == 1){
                            when (index) {
                                0 -> {
                                    container0.addView(generateBall("").apply {
                                        visibility = View.INVISIBLE
                                    })
                                    container0.addView(generateBall(it))
                                }
                                predictions?.lastIndex -> {
                                    container0.addView(generateBall(it))
                                    container0.addView(generateBall("").apply {
                                        visibility = View.INVISIBLE
                                    })
                                    drawResult.addView(container0)
                                }
                                else -> {
                                    container0.addView(generateBall(it))
                                }
                            }
                        }else {
                            val container = generateLinearLayoutHorizontal()
                            it.forEach { c ->
                                container.addView(generateBall(c.toString()))
                            }
                            drawResult.addView(container)
                        }
                    }
                }
                else -> {
                    var container = generateLinearLayoutHorizontal()
                    predictions?.forEachIndexed { index, s ->
                        container.addView(generateBall(s))
                        if((index+1) % 5 == 0 && index != 0){
                            drawResult.addView(container)
                            container = generateLinearLayoutHorizontal()
                        }
                    }
                }
            }

        }
    }

    private fun generateLinearLayoutHorizontal(): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
    }

    private fun generateBall(number: String): View {
        return LayoutInflater.from(context).inflate(R.layout.item_ball, null, false).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0F
            )
            findViewById<CardView>(R.id.cardViewBall).setCardBackgroundColor(generateRandomColor())
            findViewById<TextView>(R.id.number).text = number
        }
    }

    private fun generateRandomColor(): Int {
        val colorList = listOf(
            R.color.app_red,
            R.color.app_gold,
            R.color.ball_blue,
            R.color.ball_darkBlue,
            R.color.ball_brown,
            R.color.ball_violet,
        )
        return ContextCompat.getColor(context, colorList.shuffled()[0])
    }


    fun show(predictions: List<String>, segment: String, date: String){
        this.predictions = predictions
        title = segment
        this.date = date
        show()
    }

    override fun onStart() {
        super.onStart()
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}