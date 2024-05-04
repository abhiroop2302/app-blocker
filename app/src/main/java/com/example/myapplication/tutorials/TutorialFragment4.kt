package com.example.myapplication.tutorials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTutorial4Binding
import com.example.myapplication.screen.TutorialsActivity
import com.example.myapplication.utility.AnimationManager
import com.example.myapplication.utility.visibility


class TutorialFragment4 : BaseTutorialFragment() {
    private lateinit var binding: FragmentTutorial4Binding
    private lateinit var animationManager: AnimationManager
    private val animationList = ArrayList<Pair<View, Animation>>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorial4Binding.inflate(layoutInflater)
        animationManager = AnimationManager()
        setAnimationList()
        return binding.root
    }

    private fun setAnimationList() {
        animationList.clear()
        animationList.apply {
            add(
                Pair(
                    binding.tvNoMoreApps, AnimationUtils.loadAnimation(
                        TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )

            add(
                Pair(
                    binding.clSection1, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )

            add(
                Pair(
                    binding.ivIcon, AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_2_animation_3)
                )
            )
        }
        animationManager.setAnimationList(animationList)
    }

    override fun resetView() {
        binding.apply {
            clSection1.visibility()
            tvNoMoreApps.visibility()
        }
    }

    override fun loadTutorialWithAnimation() {
        animationManager.startAnimation()
    }

    companion object{
        private var instance:TutorialFragment4?=null
        fun getInstance():TutorialFragment4{
            if(instance != null)
                return instance!!
            instance = TutorialFragment4()
            return instance!!
        }
    }

}