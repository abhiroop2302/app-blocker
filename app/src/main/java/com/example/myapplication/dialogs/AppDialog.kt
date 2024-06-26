package com.example.myapplication.dialogs

import androidx.viewbinding.ViewBinding

interface  AppDialog {
    fun initializeDialog(viewBinding: ViewBinding)
    fun register(dialogResultManager: DialogResultManager)
    fun show()
}