package com.example.myapplication.tutorials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTutorial1Binding
import com.example.myapplication.screen.TutorialsActivity
import com.example.myapplication.utility.AnimationManager
import com.example.myapplication.utility.visibility

class TutorialFragment1 : BaseTutorialFragment() {

    private lateinit var binding: FragmentTutorial1Binding
    private lateinit var animationManager: AnimationManager
    private val animationList = ArrayList<Pair<View, Animation>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorial1Binding.inflate(layoutInflater)
        animationManager = AnimationManager()
        setAnimationList()
        return binding.root
    }

    private fun setAnimationList() {
        animationList.clear()
        animationList.apply {
            add(
                Pair(
                    binding.tvTitle,
                    AnimationUtils.loadAnimation(
                        TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )
            add(
                Pair(
                    binding.tvNoMoreApps,
                    AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )
            add(
                Pair(
                    binding.ivLogo,
                    AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )
            add(
                Pair(
                    binding.tvWellBeing,
                    AnimationUtils.loadAnimation(TutorialsActivity.getContext()
                        , R.anim.tutorial_1_animation_1)
                )
            )
        }
        animationManager.setAnimationList(animationList)
    }

    override fun resetView() {
        binding.apply {
            tvTitle.visibility()
            tvNoMoreApps.visibility()
            tvWellBeing.visibility()
            ivLogo.visibility()
        }
    }

    override fun loadTutorialWithAnimation() {
        animationManager.startAnimation()
    }

    companion object {

        private var instance: TutorialFragment1? = null
        fun getInstance(): TutorialFragment1 {
            if (instance != null)
                return instance!!

            instance = TutorialFragment1()
            return instance!!
        }

    }
}