package com.allwin.haugiang.common.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.allwin.haugiang.R
import com.allwin.haugiang.common.extensions.writeLogs
import com.allwin.haugiang.common.utils.BallDirection
import com.allwin.haugiang.common.utils.Timer
import com.allwin.haugiang.databinding.ItemBallBinding

class LottoBallView: ConstraintLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {  }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) { }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {}

    var binding: ItemBallBinding
    var randomColor = generateRandomColor()
    var ballSize = 100
    private var scale = 10
    private var direction = generateRandomDirection()
    var ballNumber = ""
    var isActive = true
    var numberSelected = false
    var hideNumber: Boolean = false
    set(value) {
        field = value
        binding.number.text = if(value) "" else ballNumber
    }

    val timer = Timer(1, object : Timer.Listener{
        override fun onTimeChange(durationMillis: Long) {
            if(onScreenBorder()){
                direction = when(direction){
                    BallDirection.UP_RIGHT,
                    BallDirection.UP_LEFT,
                    BallDirection.UP ->  generateRandomDirection("DOWN_")
                    BallDirection.DOWN_RIGHT,
                    BallDirection.DOWN_LEFT,
                    BallDirection.DOWN -> generateRandomDirection("UP_")
                    BallDirection.LEFT -> generateRandomDirection("_RIGHT")
                    BallDirection.RIGHT -> generateRandomDirection("_LEFT")
                }
                elevation = (0..55).random().toFloat()
            }
            val slope = randomAngle(true)
            x += direction.x * (scale * slope)
            y += direction.y * (scale * slope)

        }

        private fun randomAngle(isX: Boolean): Float{
            val angle = Math.random().toFloat()
            writeLogs("Random $angle")
            return when(direction){
                BallDirection.UP_RIGHT,
                BallDirection.UP_LEFT,
                BallDirection.DOWN_RIGHT,
                BallDirection.DOWN_LEFT -> if(isX) angle else 1F
                else -> 1F
            }
        }

        private fun onScreenBorder(): Boolean {
            return if(isAttachedToWindow){
                val parent = parent as ViewGroup
                val downBounds = parent.height.toFloat() - ballSize
                x <= 0F && getDirections("LEFT") ||
                        x >= parent.width - ballSize && getDirections("RIGHT") ||
                        y >= downBounds && getDirections("DOWN") ||
                        y <= 0F && getDirections("UP")
            }else false
        }

        private fun getDirections(directionStr: String): Boolean {
            return BallDirection.values().filter { it.name.contains(directionStr) }.any { d -> d == direction }
        }
    })

    init {
        inflate(context, R.layout.item_ball, this)
        binding = ItemBallBinding.bind(this)
        post {
            if(isActive) {
                generateRandomPosition()
                timer.start()
            }
            else timer.stop()

//            binding.number.text = ballNumber
            binding.cardViewBall.setCardBackgroundColor(randomColor)

        }
    }

    fun generateRandomPosition() {
        x = (0..(parent as View).width).random().toFloat()
        y = (0..(parent as View).width).random().toFloat()
        randomColor = generateRandomColor()
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timer.stop()
        writeLogs("stop timer")
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

    private fun generateRandomDirection(direction: String): BallDirection {
        return BallDirection.values().filter {
            it.name.contains(direction, true)
        }.random()
    }

    private fun generateRandomDirection(): BallDirection {
        return BallDirection.values().random()
    }

    fun reset(){
        timer.stop()
        isActive = false
        hideNumber = false
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1F
        )
        setOnClickListener(null)
        x = 0F
        y = 0F
    }

    fun clearPadding(){
        binding.root.setPadding(0)
        binding.container.setPadding(0)
        setPadding(0)
    }

}