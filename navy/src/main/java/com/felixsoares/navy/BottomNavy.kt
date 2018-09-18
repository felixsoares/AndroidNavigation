package com.felixsoares.mybottomnavy

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout

class BottomNavy : LinearLayout {

    val itens = ArrayList<ItemNavy>()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        orientation = LinearLayout.HORIZONTAL
    }

    fun addItem(item: ItemNavy): BottomNavy {
        itens.add(item)
        return this
    }

    fun build(listener: OnTabSelectedListener) {
        for (i in 0..(itens.size - 1)) {
            val outValue = TypedValue()
            context!!.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)

            val item = itens[i].addContext(context).build()
            item.setBackgroundResource(outValue.resourceId)
            item.setOnClickListener {
                deselect()
                select(i)
                listener.onTabSelected(i)
            }

            if (i == 0) {
                select(i)
            }

            addView(item)
        }
    }

    fun hideAlert() {
        for (i in 0..(itens.size - 1)) {
            if (itens[i].hasAlert) {
                itens[i].hideAlert()
            }
        }
    }

    fun showAlert() {
        for (i in 0..(itens.size - 1)) {
            if (itens[i].hasAlert) {
                itens[i].showAlert()
            }
        }
    }

    fun addBadge(value: Int) {
        for (i in 0..(itens.size - 1)) {
            if (itens[i].hasBadge) {
                itens[i].addBadge(value)
            }
        }
    }

    private fun deselect() {
        itens.forEach { it.deselect() }
    }

    private fun select(position: Int) {
        itens[position].select()
    }

    interface OnTabSelectedListener {
        /**
         * Called when a tab click.
         *
         * @param position The position of the tab that was selected
         */
        fun onTabSelected(position: Int)
    }

}
