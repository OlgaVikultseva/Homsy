package com.example.homsy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class VerticalTextView1(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = currentTextColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight)
//        // меняем значения местами
//        setMeasuredDimension(measuredHeight, measuredWidth)
    }



    override fun onDraw(canvas: Canvas) {
        canvas.save()

        val text = super.getText().toString()

        //    val bounds = Rect()
        //    textPaint.getTextBounds(text, 0, text.length, bounds)

        // устанавливаем pivot
//        canvas.translate(measuredWidth.toFloat(), measuredHeight.toFloat())
//        // поворачиваем на 270 градусов
//        canvas.rotate(270f)

        //        val x = paddingStart.toFloat()
//        val y = (measuredHeight - paddingTop - paddingBottom) / 2f + paddingTop
//        canvas.drawText(text, x, y, textPaint)
    }
}