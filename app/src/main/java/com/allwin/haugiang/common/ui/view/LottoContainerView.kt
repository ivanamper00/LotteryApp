package com.allwin.haugiang.common.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.core.view.get
import com.allwin.haugiang.R
import com.allwin.haugiang.common.extensions.writeLogs
import com.allwin.haugiang.common.utils.BallDirection
import com.allwin.haugiang.common.utils.Timer
import com.allwin.haugiang.databinding.CustomLottoContainerBinding

class LottoContainerView : ConstraintLayout {

    private var binding: CustomLottoContainerBinding = CustomLottoContainerBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    var maxNumber = 55
    var minNumber = 0
    var numberOfDraw = 6
    var repeatableDraw = false
    var ballSize = 100F
    private var ballsList: List<LottoBallView> = emptyList()
    private var selectedBalls: MutableList<LottoBallView> = mutableListOf()
    var listener: Listener? = null

    private var autoPickBall : LottoBallView? = null

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        startLayout(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {}

    private fun startLayout(attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LottoContainerView, 0, 0
        )
        try {
            //get the text and colors specified using the names in attrs.xml
            maxNumber = a.getInt(R.styleable.LottoContainerView_MaxNumber, 55)
            minNumber = a.getInt(R.styleable.LottoContainerView_MinNumber, 0)
            repeatableDraw = a.getBoolean(R.styleable.LottoContainerView_repeatableResult, false)
            numberOfDraw = a.getInt(R.styleable.LottoContainerView_numberOfDraw, 6)
            ballSize = a.getDimension(R.styleable.LottoContainerView_ballSize, 100F)
            setupView()
        } finally {
            a.recycle()
        }
    }

    private fun setupView() {
        post {
            with(binding){
                val linear1 = generateLinear()
                val linear2 = generateLinear().apply {
                    visibility = View.GONE
                }
                drawResult.apply {
                   orientation = LinearLayout.VERTICAL
                    addView(linear1)
                    addView(linear2)
                }

                ballsList = (minNumber..maxNumber).map {
                    generateBall(it.toString()).apply {
                        layoutParams = RelativeLayout.LayoutParams(
                            ballSize,
                            ballSize,
                        )
                    }
                }
                ballsList.forEach {
                    it.apply {
                        it.hideNumber = true
                        setOnClickListener { v ->
                            val current = v as LottoBallView
                            if(selectedBalls.size < numberOfDraw){
                                val selectedItem = ballsList.find { b -> !b.hideNumber }
                                if(selectedItem != null){
                                    saveNumber(linear1, linear2, selectedItem)
                                }else {
                                    current.hideNumber = !current.hideNumber
                                    if(!current.hideNumber) elevation = if (maxNumber > 55) maxNumber.toFloat() else 55F
                                }
                                if(selectedBalls.size == numberOfDraw) {
                                    listener?.onDone(selectedBalls.map { n -> n.ballNumber })
                                    return@setOnClickListener
                                }
                                stopBalls()
                            }
                        }
                    }
                    drawContainer.addView(it)
                }
            }
        }
    }

    private fun saveNumber(linear1: LinearLayout, linear2: LinearLayout, it: LottoBallView) {

        if(!repeatableDraw) binding.drawContainer.removeView(it)
        else it.generateRandomPosition()
        selectedBalls.add(it)

        if(selectedBalls.size <= 10){
            linear1.removeViewAt(selectedBalls.size - if(numberOfDraw == 3) 0 else 1)
            linear1.addView(
                generateBall(it.ballNumber).apply {
                    if(numberOfDraw == 20) clearPadding()
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1F
                    )
                    randomColor = it.randomColor
                    isActive = false
                    hideNumber = false
                },
                selectedBalls.lastIndex + if(numberOfDraw == 3) 1 else 0
            )
        }else {
            linear2.visibility = View.VISIBLE
            linear2.removeViewAt((selectedBalls.size - 10) - if(numberOfDraw == 3) 0 else 1)
            linear2.addView(
                generateBall(it.ballNumber).apply {
                    if(numberOfDraw == 20) clearPadding()
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1F
                    )
                    randomColor = it.randomColor
                    isActive = false
                    hideNumber = false
                },
                (selectedBalls.lastIndex - 10) + if(numberOfDraw == 3) 1 else 0
            )
        }

        hideAllNumber()

        listener?.onNewItemSelected(it.ballNumber, selectedBalls.map { n -> n.ballNumber })
    }

    private fun hideAllNumber() {
        ballsList.forEach {
            it.hideNumber = true
        }
    }

    fun autoPickBall(){
        if(autoPickBall != null){
            autoPickBall?.performClick()
            autoPickBall = null
        }else {
            autoPickBall = ballsList.random()
            autoPickBall?.performClick()
        }
    }

    fun stopBalls(){
        ballsList.forEach {
            if(it.isActive) it.timer.stop()
            else it.timer.start()
            it.isActive = !it.isActive
        }
    }

    private fun generateLinear(): LinearLayout {
        return LinearLayout(context).apply {
            weightSum = if(numberOfDraw == 20) 10F else  6F
            (1..if(numberOfDraw == 3) 5 else if(numberOfDraw == 20) 10 else numberOfDraw).forEach {
                addView(generateBallsSatic("").apply {
                    visibility = View.INVISIBLE
                })
            }
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
    }

    private fun generateBallsSatic(number: String): View {
        return LayoutInflater.from(context).inflate(R.layout.item_ball, null, false).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
            findViewById<CardView>(R.id.cardViewBall).setCardBackgroundColor(ContextCompat.getColor(context, R.color.app_gold))
            findViewById<TextView>(R.id.number).text = number
        }
    }

    private fun generateBall(toString: String): LottoBallView {
        return LottoBallView(context).apply {
            ballNumber = toString
            ballSize = this@LottoContainerView.ballSize.toInt()
            isActive = true
        }
    }

    interface Listener {
        fun onNewItemSelected(ballNumber: String, ballList: List<String>)
        fun onDone(ballList: List<String>)
    }

}