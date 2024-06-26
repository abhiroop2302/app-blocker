package com.example.myapplication.dialogs

import androidx.viewbinding.ViewBinding

interface  BindingManager {
    fun initializeDialog(viewBinding: ViewBinding)
    fun register(dialogResultManager: DialogResultManager)

    fun show()
}