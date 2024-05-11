package com.example.myapplication.application

import android.app.Application

class MainApplication :Application(){
    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()

    }

    companion object{
        private lateinit var  instance:MainApplication
        fun getInstance() = instance
    }
}