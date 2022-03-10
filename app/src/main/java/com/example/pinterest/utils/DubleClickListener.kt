package com.example.pinterest.utils

import android.os.SystemClock.elapsedRealtime
import android.view.View

abstract class DoubleClickListener(
    private val doubleClickQualificationTime: Long = 300
) : View.OnClickListener {

    private var timestampLastClick = 0L

    /***/
    override fun onClick(v: View) {
        if ((elapsedRealtime() - timestampLastClick) < doubleClickQualificationTime)
            onDoubleClick(v)
        timestampLastClick = elapsedRealtime();
    }

    /** When the view is double clicked */
    abstract fun onDoubleClick(v: View)
}

/**
 * Creates a [DoubleClickListener] and applies it to a view
 */
inline fun View.setOnDoubleClickListener(
    crossinline onDoubleClick: (View) -> Unit
) {
    setOnClickListener(object : DoubleClickListener() {
        override fun onDoubleClick(v: View) {
            onDoubleClick(v)
        }
    })
}