package com.example.homsy

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams

fun View.setTopMargin(px: Int) {
    this.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = px
    }
}

fun View.setBottomMargin(px: Int) {
    this.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        bottomMargin = px
    }
}

fun Context.pxFromDp(dp: Int): Int =
    (dp * resources.displayMetrics.density).toInt()

fun Context.dpFromPx(px: Int): Int =
    (px / resources.displayMetrics.density).toInt()