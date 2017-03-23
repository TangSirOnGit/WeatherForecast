package com.tv.forecast.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.tv.forecast.R

/**
 * TODO: document your custom view class.
 */
class PageIndicatorView : LinearLayout {
    val TAG = "PageIndicatorView"
    val indicator_width = 12
    val indicator_height = 12
    val indicator_margin = 8
    var pageCount:Int = 0


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.page_indicator_layout, this, true)
    }

    fun setPageNum(num: Int){
        pageCount = num
        updateIndicators()
    }

    fun updateIndicators() {
        this.removeAllViews()
        val indicatorLp = LinearLayout.LayoutParams(indicator_width, indicator_height)
        indicatorLp.leftMargin = indicator_margin
        for (index in 0..this.pageCount - 1) {
            val indicator = ImageView(context)
            indicator.layoutParams = indicatorLp
            this.addView(indicator, index)
        }
    }

    fun updateIndicator(focusIndex: Int) {
        for (index in 0..pageCount - 1) {
            val imageView = this.getChildAt(index) as ImageView
            if (focusIndex == index) {
                imageView.setBackgroundResource(R.drawable.image_indicator_focus)
            } else {
                imageView.setBackgroundResource(R.drawable.image_indicator_normal)
            }
        }
    }

}
