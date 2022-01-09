package com.example.homsy

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class VerticalTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val text by lazy { getText().toString() }
    private val textHeight by lazy { paint.descent() - paint.ascent() }
    private val xCoordinate by lazy { paddingStart.toFloat() }
    private val yOffset by lazy { textHeight / 2 - paint.descent() }
    private val yCoordinate by lazy { paddingTop + width / 2 + yOffset }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = currentTextColor
        canvas.translate(0f, height.toFloat())
        canvas.rotate(270f, 0f, 0f)
        canvas.drawText(text, xCoordinate, yCoordinate, paint)
    }
}