package com.allwin.haugiang.common.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Date.getLongDay(): Long = this.convert().time

fun Date.convert(format: String = "MM/dd/yyyy"): Date{
    val formatter = SimpleDateFormat(format, Locale.ROOT)
    val item = formatter.format(this)
    return formatter.parse(item) ?: Date()
}

fun Date.convertToString(format: String = "MM/dd/yyyy"): String{
    val formatter = SimpleDateFormat(format, Locale.ROOT)
    return formatter.format(this)
}