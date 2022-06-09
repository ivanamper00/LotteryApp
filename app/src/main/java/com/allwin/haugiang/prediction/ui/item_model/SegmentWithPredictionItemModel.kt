package com.allwin.haugiang.prediction.ui.item_model

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.allwin.haugiang.R
import com.allwin.haugiang.databinding.ItemSegmentBinding
import com.allwin.haugiang.databinding.ItemSegmentPredictionBinding
import com.allwin.haugiang.prediction.domain.model.SegmentModel
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

@EpoxyModelClass(
    layout = R.layout.item_segment_prediction
)
abstract class SegmentWithPredictionItemModel: EpoxyModelWithHolder<SegmentWithPredictionItemModel.Holder>() {

    @EpoxyAttribute
    var segment: SegmentModel? = null

    @EpoxyAttribute
    var onItemClick: (() -> Unit)? = null

    @EpoxyAttribute
    var predictions: List<String>? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder.binding){

            segmentImage.setImageResource(segment?.imageResource ?: 0)

            root.setOnClickListener {
                onItemClick?.invoke()
            }

            drawResult.removeAllViews()
            when (predictions?.size) {
                6 -> {
                    val container = generateLinearLayoutHorizontal(root.context)
                    container.weightSum = 6F
                    predictions?.forEach {
                        container.addView(generateBall(root.context, it))
                    }
                    drawResult.addView(container)
                }
                3 -> {
                    predictions?.forEachIndexed { index, s ->
                        val container = generateLinearLayoutHorizontal(root.context)
                        container.addView(
                            generateBall(root.context,"").apply {
                                visibility = View.INVISIBLE
                            }
                        )
                        container.addView(
                            generateTextView(root.context, "Draw $index: ")
                        )
                        s.forEach { c ->
                            container.addView(
                                generateBall(root.context, c.toString())
                            )
                        }
                        container.addView(
                            generateBall(root.context,"").apply {
                                visibility = View.INVISIBLE
                            }
                        )
                        drawResult.addView(container)
                    }
                }
                else -> {

                    val keyGenerator : KeyGenerator
                    val secretKey : SecretKey
                    var container = generateLinearLayoutHorizontal(root.context)
                    predictions?.forEachIndexed { index, s ->
                        container.addView(generateBall(root.context, s))
                        if((index+1) % 5 == 0 && index != 0){
                            drawResult.addView(container)
                            container = generateLinearLayoutHorizontal(root.context)
                        }
                    }
                }
            }
        }
    }

    private fun generateTextView(context: Context, title: String): TextView{
        return (LayoutInflater.from(context).inflate(R.layout.item_text_label, null, false) as TextView).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0F
            )
            gravity = Gravity.CENTER
            text = title
        }
    }

    private fun generateLinearLayoutHorizontal(context: Context): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
    }

    private fun generateBall(context: Context, number: String): View {
        return LayoutInflater.from(context).inflate(R.layout.item_ball, null, false).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0F
            )
            findViewById<CardView>(R.id.cardViewBall).setCardBackgroundColor(generateRandomColor(context))
            findViewById<TextView>(R.id.number).text = number
        }
    }

    private fun generateRandomColor(context: Context): Int {
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

    class Holder: EpoxyHolder() {
        lateinit var binding: ItemSegmentPredictionBinding
        override fun bindView(itemView: View) {
           binding = ItemSegmentPredictionBinding.bind(itemView)
        }
    }
}