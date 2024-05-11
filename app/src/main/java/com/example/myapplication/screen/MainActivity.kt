package com.example.myapplication.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.manager.NavigationManager
import com.example.myapplication.utility.setStatusBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationManager: NavigationManager
    override fun onCreate(savedInstanceState: Bundle?) {

        window.setStatusBar(this, ContextCompat.getColor(this, R.color.white))
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        navigationManager = NavigationManager(binding.bottomBar,supportFragmentManager)
        setContentView(binding.root)

    }
}