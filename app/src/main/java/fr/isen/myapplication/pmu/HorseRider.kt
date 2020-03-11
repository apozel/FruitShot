package fr.isen.myapplication.pmu

import android.content.Context
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_pmu.*

class HorseRider(var idColor: CardColor, var startPosition: Float) {

    var startLayout: ConstraintSet = ConstraintSet()
    var currentLayout: ConstraintSet = ConstraintSet()
    var position: Int = 0
    var result: Int = 0
    var asFinish: Boolean = false

    fun setStartLayout(constraintLayout: ConstraintLayout) {
        startLayout.clone(constraintLayout)
        currentLayout.clone(constraintLayout)
    }

    fun moveForward() {
        position++
    }

    fun getBiasPosition():Float {
       return startPosition - 0.1f * position
    }

    fun finishLine(result:Int){
        this.result = result
        this.asFinish = true
    }


    fun reset() {
        position = 0
        result = 0
        asFinish = false
    }

}
