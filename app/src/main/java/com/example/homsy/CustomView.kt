package com.example.homsy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val defaultWidth = 600
    private val defaultHeight = 600

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.EXACTLY -> {
                Log.d("CustomView", "Width mode: EXACTLY")
                MeasureSpec.getSize(widthMeasureSpec)
            }
            MeasureSpec.AT_MOST -> {
                Log.d("CustomView", "Width mode: AT_MOST")
                min(MeasureSpec.getSize(widthMeasureSpec), defaultWidth)
            }
            else -> {
                Log.d("CustomView", "Width mode: UNSPECIFIED")
                defaultWidth
            }
        }

        val height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> {
                Log.d("CustomView", "Height mode: EXACTLY")
                MeasureSpec.getSize(heightMeasureSpec)
            }
            MeasureSpec.AT_MOST ->{
                Log.d("CustomView", "Height mode: AT_MOST")
                min(MeasureSpec.getSize(heightMeasureSpec), defaultHeight)
            }
            else -> {
                Log.d("CustomView", "Height mode: UNSPECIFIED")
                defaultHeight
            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawARGB(255, 128, 128, 128)

        val paint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeWidth = 6f
        }

        canvas.drawRect(100f, 100f, 200f, 250f, paint)



    }

}