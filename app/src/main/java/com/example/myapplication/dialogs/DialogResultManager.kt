package com.example.myapplication.dialogs

interface DialogResultManager {
    fun <T:Any> onResult(data:T)
}
