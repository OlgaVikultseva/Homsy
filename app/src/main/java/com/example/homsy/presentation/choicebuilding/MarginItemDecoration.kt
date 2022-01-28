package com.example.homsy.presentation.choicebuilding

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homsy.pxFromDp

class MarginItemDecoration(
    private val context: Context,
    private val topMarginDp: Int,
    private val internalMarginDp: Int,
    private val bottomMarginDp: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter
        val itemPosition = parent.getChildAdapterPosition(view)

        if (itemPosition == 0) {
            outRect.top = context.pxFromDp(topMarginDp)
        } else {
            outRect.top = context.pxFromDp(internalMarginDp)
        }

        if (itemPosition == adapter!!.itemCount - 1) {
            outRect.bottom = context.pxFromDp(bottomMarginDp)
        }
    }
}