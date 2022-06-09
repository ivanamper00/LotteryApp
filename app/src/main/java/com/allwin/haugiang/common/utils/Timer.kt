package com.allwin.haugiang.common.utils

import android.os.Handler
import android.os.Looper
import com.allwin.haugiang.common.extensions.writeLogs

class Timer(private val delay: Long, private val listener: Listener?) {

    private var isRunning = false
    private val handler = Handler(Looper.getMainLooper())
    private var currentTime = 0L

    fun start(){
        isRunning = true
        startTimer()
    }

    private fun startTimer() {
        handler.postDelayed(
            {
                currentTime += delay
                listener?.onTimeChange(currentTime)
                writeLogs("Timer: $currentTime")
                if(isRunning) startTimer()
            }
            , delay)
    }

    fun stop(){
        isRunning = false
    }

    interface Listener {
        fun onTimeChange(durationMillis: Long)
    }
}