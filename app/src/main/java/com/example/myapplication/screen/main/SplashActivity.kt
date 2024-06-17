package com.example.myapplication.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.utility.Constants
import com.example.myapplication.utility.setStatusBar

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setStatusBar(this, ContextCompat.getColor(this, R.color.app_color))
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigate()
    }

    private fun navigate() {
        Handler().postDelayed(Runnable {
         startActivity(Intent(this, MainActivity::class.java))
         finish()
        },Constants.SPLASH_TIME_OUT)
    }
}