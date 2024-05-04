package com.example.myapplication.tutorials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTutorial3Binding
import com.example.myapplication.screen.TutorialsActivity
import com.example.myapplication.utility.AnimationManager
import com.example.myapplication.utility.visibility


class TutorialFragment3 : BaseTutorialFragment() {
    private lateinit var binding: FragmentTutorial3Binding
    private lateinit var animationManager:AnimationManager
    private val animationList = ArrayList<Pair<View, Animation>>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorial3Binding.inflate(layoutInflater)
        animationManager = AnimationManager()
        setAnimationList()
        return binding.root
    }

    private fun setAnimationList() {
        animationList.clear()
        animationList.apply {
            add(
                Pair(
                    binding.clSection1, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )

            add(
                Pair(
                    binding.clSection2, AnimationUtils.loadAnimation(
                        TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )
        }
        animationManager.setAnimationList(animationList)
    }


    override fun resetView() {
        binding.apply {
            clSection2.visibility()
            clSection1.visibility()
        }
    }

    override fun loadTutorialWithAnimation() {
        animationManager.startAnimation()
    }



    companion object{
        private var instance:TutorialFragment3?=null
        fun getInstance():TutorialFragment3{
            if(instance != null)
                return instance!!
            instance = TutorialFragment3()
            return instance!!
        }
    }
}