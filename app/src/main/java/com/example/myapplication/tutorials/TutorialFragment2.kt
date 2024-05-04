package com.example.myapplication.tutorials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTutorial2Binding
import com.example.myapplication.screen.TutorialsActivity
import com.example.myapplication.utility.AnimationManager
import com.example.myapplication.utility.visibility


class TutorialFragment2 : BaseTutorialFragment() {

    private lateinit var binding: FragmentTutorial2Binding
    private val animationList:ArrayList<Pair<View, Animation>> = ArrayList()
    private lateinit var animationManager: AnimationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorial2Binding.inflate(layoutInflater)
        animationManager = AnimationManager()
        setAnimationList()
        return binding.root
    }

    private fun setAnimationList() {
        animationList.clear()
        animationList.apply {
            add(
                Pair(
                    binding.clBlock, AnimationUtils.loadAnimation(
                        TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_1)
                )
            )
            add(
                Pair(
                    binding.clApps, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_1)
                )
            )
            add(
                Pair(
                    binding.clUsage, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_1)
                )
            )
            add(
                Pair(
                    binding.tvNoDistraction, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_1)
                )
            )
            add(
                Pair(
                    binding.ivPinch, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_2)
                )
            )
            add(
                Pair(
                    binding.llBlocked, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_2
                    )
                )
            )
            add(
                Pair(
                    binding.ivPinchIcon, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_3
                    )
                )
            )
        }
        animationManager.setAnimationList(animationList)
    }

    override fun resetView() {
        binding.apply {
            clBlock.visibility()
            clApps.visibility()
            clUsage.visibility()
            tvNoDistraction.visibility()
            ivPinch.visibility()
            llBlocked.visibility()
            ivPinchIcon.visibility()
        }
    }

    override fun loadTutorialWithAnimation() {
        animationManager.startAnimation()
    }

    companion object{

        private var instance:TutorialFragment2?= null
        fun getInstance():TutorialFragment2{
            if(instance!= null)
                return instance!!

            instance = TutorialFragment2()
            return instance!!
        }

    }

}