package com.tv.forecast.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.ViewFlipper
import com.tv.forecast.R
import com.tv.forecast.utils.DisplayUtil

class MarqueeView : ViewFlipper {

    private var isSetAnimDuration = false

    private var interval = 5000
    private var animDuration = 600
    private var textSize = 18
    private var textColor = 0xffffffff.toInt()

    private var singleLine = false
    var gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL

    constructor(context: Context) : super(context) {
        init(null, 0)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }


    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0)
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval)
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration)
        singleLine = typedArray.getBoolean(R.styleable.MarqueeViewStyle_mvSingleLine, false)
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration)
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize.toFloat()).toInt()
            textSize = DisplayUtil.px2sp(context, textSize.toFloat())
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor)
        val gravityType = typedArray.getInt(R.styleable.MarqueeViewStyle_mvGravity, TEXT_GRAVITY_LEFT)
        when (gravityType) {
            TEXT_GRAVITY_CENTER -> gravity = Gravity.CENTER
            TEXT_GRAVITY_RIGHT -> gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        }
        typedArray.recycle()

        setFlipInterval(interval)
    }


    // 根据公告字符串列表启动轮播
    fun startWithList(notices: List<String>) {
        //setNotices(notices)
        start(notices)
    }

    // 启动轮播
    fun start(notices: List<String>): Boolean {
        if (notices.isEmpty()) return false
        removeAllViews()
        resetAnimation()

        notices.forEach { addView(createTextView(it)) }

        if (notices.size > 1) {
            startFlipping()
        } else {
            stopFlipping()
        }
        return true
    }

    private fun resetAnimation() {
        clearAnimation()

        val animIn = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_in)
        if (isSetAnimDuration) animIn.duration = animDuration.toLong()
        inAnimation = animIn

        val animOut = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_out)
        if (isSetAnimDuration) animOut.duration = animDuration.toLong()
        outAnimation = animOut
    }

    // 创建ViewFlipper下的TextView
    private fun createTextView(text: CharSequence): TextView {
        val tv = TextView(context)
        tv.gravity = gravity
        tv.text = text
        tv.setTextColor(textColor)
        tv.textSize = textSize.toFloat()
        tv.setSingleLine(singleLine)
        return tv
    }

    companion object {
        private val TEXT_GRAVITY_LEFT = 0
        private val TEXT_GRAVITY_CENTER = 1
        private val TEXT_GRAVITY_RIGHT = 2
    }

}
