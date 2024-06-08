package com.example.myapplication.screen.add_block_section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.utility.setStatusBar

class AddRestrictionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setStatusBar(this, ContextCompat.getColor(this, R.color.white))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restriction)
    }
}