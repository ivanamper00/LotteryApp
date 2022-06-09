package com.allwin.haugiang.common.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

const val TAG = "HAUGIANG"

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}

fun writeLogs(message: String){
    Log.d(TAG, message)
}