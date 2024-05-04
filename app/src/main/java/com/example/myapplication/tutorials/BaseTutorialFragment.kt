package com.example.myapplication.tutorials

import androidx.fragment.app.Fragment

/**
 *  this is the abstract class and is used for the tutorial purpose because everytime the fragment for the tutorial loads then it
 *  causes the element to animate again so because of this we have used this
 *
 */
abstract class BaseTutorialFragment:Fragment() {

    // function is used to make the visibility of the views for the fragment again to the invisible state.
    abstract fun resetView()

    // function which called to start the animation of the elements for the given fragment.
    abstract fun loadTutorialWithAnimation()
}