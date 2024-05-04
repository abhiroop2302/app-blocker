package com.example.myapplication.utility

import android.util.Log
import android.view.View


fun String.debugLog(loggerTag: String) {
    Log.d(loggerTag, this)
}


fun View.visibility(visibility: Int = View.INVISIBLE) {
    this.visibility = visibility
}