package com.allwin.haugiang.common.extensions

import android.widget.ImageView
import com.allwin.haugiang.R
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_image_broken)
        .into(this)
}