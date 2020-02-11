package fr.isen.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_jus_fruit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class JusFruitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jus_fruit)


        val timer = object: CountDownTimer(8500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondLeft  = (millisUntilFinished.toFloat()/1000).roundToInt()
                timerView.text = secondLeft.toString()
            }

            override fun onFinish() {
                //TODO afficher les boutons OK/NOK et afficher une autre question
            }
        }
        timer.start()



    }





}
