package com.example.myapplication.utility
import androidx.activity.result.ActivityResultLauncher

/**
 *  This class is responsible for the creation of the
 *  contracts -> which is used for the different purpose.
 *
 *  Ask Permission , Ask Multiple Permission , Fetch Image from Gallery etc.
 *
 *  It is not the generic yet !!.
 */
object ContractsUtils {
    private lateinit var notificationPermissionContract: ActivityResultLauncher<String>

    fun setNotificationContract(notificationContract:ActivityResultLauncher<String>){
        notificationPermissionContract = notificationContract
    }

    fun getContract()= notificationPermissionContract


}