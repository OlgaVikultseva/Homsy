package com.example.homsy

import android.content.Context

fun Context.pxFromDp(dp: Int): Int =
    (dp * resources.displayMetrics.density).toInt()