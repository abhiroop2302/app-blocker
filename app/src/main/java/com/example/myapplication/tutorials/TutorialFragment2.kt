package com.example.myapplication.tutorials

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTutorial1Binding
import com.example.myapplication.databinding.FragmentTutorial2Binding


class TutorialFragment2 : Fragment() {
    private lateinit var binding: FragmentTutorial2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorial2Binding.inflate(layoutInflater)
        return binding.root
    }
}