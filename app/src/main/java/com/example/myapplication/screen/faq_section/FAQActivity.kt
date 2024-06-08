package com.example.myapplication.screen.faq_section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.utility.setStatusBar

class FAQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setStatusBar(this, ContextCompat.getColor(this, R.color.white))
        setContentView(R.layout.activity_faqactivity)
    }
}