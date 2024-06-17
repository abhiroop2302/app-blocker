package com.example.myapplication.screen.upgrade_section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.abstract_managers.BaseRecyclerView
import com.example.myapplication.abstract_managers.GenericRecyclerView
import com.example.myapplication.databinding.ActivityUpgradeBinding
import com.example.myapplication.utility.Constants
import com.example.myapplication.utility.setStatusBar

class UpgradeActivity : AppCompatActivity(), BaseRecyclerView by UpgradeFeatureRecyclerImplementor() {

    private val binding:ActivityUpgradeBinding by lazy {
        ActivityUpgradeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setStatusBar(this, ContextCompat.getColor(this, R.color.white))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        initialize()

    }

    private fun initialize() {
        com.example.myapplication.utility.runOnBackgroundThread {
            fetchUpgradeFeatureList {
                setData(it)
                binding.rlUpgradeItems.adapter = GenericRecyclerView(
                    this
                )
            }
        }
    }

    private fun setClickListener() {
        binding.apply {
            ivClose.setOnClickListener { finish() }
            clUpgrade.setOnClickListener {
                Constants.NO_IMPLEMENTATION_YET
            }
        }
    }
}