package com.example.myapplication.utility

import android.app.Activity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Activity.runOnMainThread(invokeCall:()->Unit){
    CoroutineScope(Dispatchers.Main).launch {
        invokeCall.invoke()
    }
}

fun Fragment.runOnMainThread(invokeCall:()->Unit){
    CoroutineScope(Dispatchers.Main).launch {
        invokeCall.invoke()
    }
}

fun runOnMainThread(invokeCall:()->Unit){
    CoroutineScope(Dispatchers.Main).launch {
        invokeCall.invoke()
    }
}

fun Activity.runOnBackgroundThread(dispatcher: CoroutineDispatcher = Dispatchers.Default, invokeCall:()->Unit){
    CoroutineScope(dispatcher).launch {
        invokeCall.invoke()
    }
}

fun Fragment.runOnBackgroundThread(dispatcher:CoroutineDispatcher = Dispatchers.Default,invokeCall:()->Unit){
    CoroutineScope(dispatcher).launch {
        invokeCall.invoke()
    }
}

fun runOnBackgroundThread(dispatcher:CoroutineDispatcher = Dispatchers.Default, invokeCall:suspend ()->Unit){
    CoroutineScope(dispatcher).launch {
        invokeCall.invoke()
    }
}