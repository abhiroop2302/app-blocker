package com.example.myapplication.recycler_view_data_modals

import android.content.Intent

data class PermissionModal(
    val permissionName:String,
    val permissionDesc:String,
    val permissionIcon:Int,
    val permissionIntent:Intent
)