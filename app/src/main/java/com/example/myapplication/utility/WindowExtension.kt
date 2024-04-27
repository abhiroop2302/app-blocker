package com.example.myapplication.utility

import android.content.Context
import android.view.Window
import androidx.core.content.ContextCompat
import com.example.myapplication.R

fun Window.setStatusBar(context: Context, color:Int){
    statusBarColor = color
}