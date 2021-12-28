package com.example.homsy

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.withRotation
import java.lang.Exception
import kotlin.math.min

class VerticalTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val _isSelected: Boolean

    init {
        val attributeArray: TypedArray =  context.obtainStyledAttributes(attrs, R.styleable.VerticalTextView)
        _isSelected = attributeArray.getBoolean(R.styleable.VerticalTextView_isSelected, false)
        attributeArray.recycle()
    }

    private val text by lazy { getText().toString() }
    private val textHeight by lazy { paint.descent() - paint.ascent() }
    private val xCoordinate by lazy { paddingStart.toFloat() }
    private val yOffset by lazy { textHeight / 2 - paint.descent() }
    private val yCoordinate by lazy { paddingTop + width / 2 + yOffset }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
//        val width = when (MeasureSpec.getMode(widthMeasureSpec)) {
//            MeasureSpec.EXACTLY -> MeasureSpec.getSize(widthMeasureSpec)
//            else -> throw Exception("Mode is not EXACTLY")
//        }
//
//        val height = when (MeasureSpec.getMode(heightMeasureSpec)) {
//            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
//            else -> throw Exception("Mode is not EXACTLY")
//        }
//
//        setMeasuredDimension(height, width)

        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {

//        canvas.drawARGB(255, 128, 128, 128)

        canvas.translate(0f, height.toFloat())
        canvas.rotate(270f, 0f, 0f)
        canvas.drawText(text, xCoordinate, yCoordinate, paint)
    }
}