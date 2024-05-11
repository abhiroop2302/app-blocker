package com.example.myapplication.utility

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.application.MainApplication
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.toggleTabColor(isSelected: Boolean) {
    setTextColor(
        when (isSelected) {
            true -> {
                ContextCompat.getColor(
                    MainApplication.getInstance().applicationContext,
                    R.color.add_color
                )

            }

            else -> ContextCompat.getColor(
                MainApplication.getInstance().applicationContext,
                R.color.black
            )
        }
    )

}


