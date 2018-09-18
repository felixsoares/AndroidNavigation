package com.felixsoares.mybottomnavy.util

import android.content.Context
import android.util.DisplayMetrics

object NavyUtil {

    const val NORMAL_SIZE_ITEM = 35
    const val BADGE_SIZE_ITEM = 40

    const val NORMAL_IMAGE_SIZE = 25
    const val SELECTED_IMAGE_SIZE = 30

    const val BADGE_SIZE = 16
    const val BADGE_TEXT_SIZE = 3

    const val ALERT_SIZE = 7

    fun convertDpToPixel(dp: Int, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return Math.round(px)
    }

}