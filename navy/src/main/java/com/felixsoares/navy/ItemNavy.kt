package com.felixsoares.mybottomnavy

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.felixsoares.mybottomnavy.util.NavyUtil
import com.felixsoares.navy.R

class ItemNavy(
        var image: Int,
        var imageSelected: Int,
        val hasBadge: Boolean = false,
        val hasAlert: Boolean = false
) {

    var context: Context? = null

    private var alert: LinearLayout? = null
    private var imageView: ImageView? = null

    private var textViewBadge: TextView? = null
    private var badge: LinearLayout? = null

    fun addContext(context: Context): ItemNavy {
        this.context = context
        return this
    }

    fun build(): View {
        val view = LinearLayout(context)
        view.gravity = Gravity.CENTER
        view.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        view.isClickable = true


        val container = RelativeLayout(context)
        container.layoutParams = if (hasBadge) {
            RelativeLayout.LayoutParams(
                    NavyUtil.convertDpToPixel(NavyUtil.BADGE_SIZE_ITEM, context!!),
                    NavyUtil.convertDpToPixel(NavyUtil.BADGE_SIZE_ITEM, context!!)
            )
        } else {
            RelativeLayout.LayoutParams(
                    NavyUtil.convertDpToPixel(NavyUtil.NORMAL_SIZE_ITEM, context!!),
                    NavyUtil.convertDpToPixel(NavyUtil.NORMAL_SIZE_ITEM, context!!)
            )
        }

        imageView = ImageView(context)
        imageView!!.setImageDrawable(ContextCompat.getDrawable(context!!, this.image))
        imageView!!.layoutParams = normalLayoutParam()

        container.addView(imageView)

        if (hasAlert) {
            createAlert()
            container.addView(alert)
        }

        if (hasBadge) {
            createBadge()
            container.addView(badge)
        }

        view.addView(container)

        return view
    }

    private fun createAlert() {
        val params = RelativeLayout.LayoutParams(
                NavyUtil.convertDpToPixel(NavyUtil.ALERT_SIZE, context!!),
                NavyUtil.convertDpToPixel(NavyUtil.ALERT_SIZE, context!!)
        )
        params.topMargin = NavyUtil.convertDpToPixel(3, context!!)
        params.addRule(RelativeLayout.ALIGN_PARENT_END)

        alert = LinearLayout(context)
        alert!!.visibility = View.GONE
        alert!!.layoutParams = params
        alert!!.background = ContextCompat.getDrawable(context!!, R.drawable.red_circle)
    }

    private fun createBadge() {
        val params = RelativeLayout.LayoutParams(
                NavyUtil.convertDpToPixel(NavyUtil.BADGE_SIZE, context!!),
                NavyUtil.convertDpToPixel(NavyUtil.BADGE_SIZE, context!!)
        )
        params.topMargin = NavyUtil.convertDpToPixel(3, context!!)
        params.addRule(RelativeLayout.ALIGN_PARENT_END)

        badge = LinearLayout(context)
        badge!!.visibility = View.GONE
        badge!!.layoutParams = params
        badge!!.gravity = Gravity.CENTER
        badge!!.background = ContextCompat.getDrawable(context!!, R.drawable.red_circle)

        val textParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )

        textViewBadge = TextView(context)
        textViewBadge!!.layoutParams = textParams
        textViewBadge!!.setTextColor(ContextCompat.getColor(context!!, android.R.color.white))
        textViewBadge!!.textSize = NavyUtil.convertDpToPixel(NavyUtil.BADGE_TEXT_SIZE, context!!).toFloat()

        badge!!.addView(textViewBadge)
    }

    private fun selectedLayoutParam(): RelativeLayout.LayoutParams {
        val params = RelativeLayout.LayoutParams(
                NavyUtil.convertDpToPixel(NavyUtil.SELECTED_IMAGE_SIZE, context!!),
                NavyUtil.convertDpToPixel(NavyUtil.SELECTED_IMAGE_SIZE, context!!)
        )
        params.addRule(RelativeLayout.CENTER_VERTICAL)
        params.addRule(RelativeLayout.CENTER_HORIZONTAL)
        return params
    }

    private fun normalLayoutParam(): RelativeLayout.LayoutParams {
        val params = RelativeLayout.LayoutParams(
                NavyUtil.convertDpToPixel(NavyUtil.NORMAL_IMAGE_SIZE, context!!),
                NavyUtil.convertDpToPixel(NavyUtil.NORMAL_IMAGE_SIZE, context!!)
        )
        params.addRule(RelativeLayout.CENTER_VERTICAL)
        params.addRule(RelativeLayout.CENTER_HORIZONTAL)
        return params
    }

    // --------
    // Actions
    // --------

    fun select() {
        imageView!!.layoutParams = selectedLayoutParam()
        imageView!!.setImageDrawable(ContextCompat.getDrawable(context!!, this.imageSelected))
    }

    fun deselect() {
        imageView!!.layoutParams = normalLayoutParam()
        imageView!!.setImageDrawable(ContextCompat.getDrawable(context!!, this.image))
    }

    fun hideAlert() {
        if (alert != null) {
            alert!!.visibility = View.GONE
        }
    }

    fun showAlert() {
        if (alert != null) {
            alert!!.visibility = View.VISIBLE
        }
    }

    fun addBadge(value: Int) {
        if (badge != null) {
            if (value > 0) {
                badge!!.visibility = View.VISIBLE
                textViewBadge!!.text = if (value >= 100) "+99" else "$value"
            } else {
                badge!!.visibility = View.GONE
            }
        }
    }

}
