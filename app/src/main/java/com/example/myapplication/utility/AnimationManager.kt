package com.example.myapplication.utility

import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener

/**
 *  this class is take care for the animation flow for the different fragments.
 */
class AnimationManager : AnimationListener {

    private var animationList : ArrayList<Pair<View, Animation>> = ArrayList()
    private var animationItemCount = -1

    companion object{
        private const val loggerTag = "AnimationManager"
    }

    override fun onAnimationStart(p0: Animation?) {}

    override fun onAnimationEnd(p0: Animation?) {
        if(animationItemCount+1 >= animationList.size)
            return

        animationItemCount += 1
        animationList[animationItemCount].first.visibility = View.VISIBLE
        animationList[animationItemCount].second.setAnimationListener(this)
        animationList[animationItemCount].first.startAnimation(animationList[animationItemCount].second)

    }

    override fun onAnimationRepeat(p0: Animation?) {

    }

    fun setAnimationList(animationList: ArrayList<Pair<View, Animation>>){
        this.animationList.clear()
        this.animationList.addAll(animationList)

    }


    fun startAnimation(){

        animationItemCount = 0
        android.os.Handler(Looper.myLooper()!!).postDelayed(Runnable {
            animationList[0].second.setAnimationListener(this)
            animationList[animationItemCount].first.visibility = View.VISIBLE
            animationList[0].first.startAnimation(animationList[0].second)
        }, 500)


    }
}