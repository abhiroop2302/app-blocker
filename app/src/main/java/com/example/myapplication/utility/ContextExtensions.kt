package com.example.myapplication.utility

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.Settings
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

fun Context.navigateToSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}


