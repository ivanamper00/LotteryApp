package com.allwin.haugiang.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(format : String = "MM/dd/yyyy"): Date {
    val formatter = SimpleDateFormat(format, Locale.ROOT)
    return formatter.parse(this) ?: Date()
}