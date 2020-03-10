package fr.isen.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_purple.*
import java.util.*

class PurpleActivity : AppCompatActivity() {

    var images: Array<String> = arrayOf<String>(
        R.drawable.banana2.toString(),
        R.drawable.banana3.toString(),
        R.drawable.banana4.toString(),
        R.drawable.banana5.toString(),
        R.drawable.banana6.toString(),
        R.drawable.banana7.toString(),
        R.drawable.banana8.toString(),
        R.drawable.banana9.toString(),
        R.drawable.banana10.toString(),
        R.drawable.bananaas.toString(),
        R.drawable.bananaj.toString(),
        R.drawable.bananak.toString(),
        R.drawable.bananaq.toString(),
        R.drawable.lemon2.toString(),
        R.drawable.lemon3.toString(),
        R.drawable.lemon4.toString(),
        R.drawable.lemon5.toString(),
        R.drawable.lemon6.toString(),
        R.drawable.lemon7.toString(),
        R.drawable.lemon8.toString(),
        R.drawable.lemon9.toString(),
        R.drawable.lemon10.toString(),
        R.drawable.lemonas.toString(),
        R.drawable.lemonj.toString(),
        R.drawable.lemonk.toString(),
        R.drawable.lemonq.toString(),
        R.drawable.strawberry2.toString(),
        R.drawable.strawberry3.toString(),
        R.drawable.strawberry4.toString(),
        R.drawable.strawberry5.toString(),
        R.drawable.strawberry6.toString(),
        R.drawable.strawberry7.toString(),
        R.drawable.strawberry8.toString(),
        R.drawable.strawberry9.toString(),
        R.drawable.strawberry10.toString(),
        R.drawable.strawberryas.toString(),
        R.drawable.strawberryj.toString(),
        R.drawable.strawberryk.toString(),
        R.drawable.strawberryq.toString(),
        R.drawable.watermelon2.toString(),
        R.drawable.watermelon3.toString(),
        R.drawable.watermelon4.toString(),
        R.drawable.watermelon5.toString(),
        R.drawable.watermelon6.toString(),
        R.drawable.watermelon7.toString(),
        R.drawable.watermelon8.toString(),
        R.drawable.watermelon9.toString(),
        R.drawable.watermelon10.toString(),
        R.drawable.watermelonas.toString(),
        R.drawable.watermelonj.toString(),
        R.drawable.watermelonk.toString(),
        R.drawable.watermelonq.toString()
    )

    //ImageView imageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purple)

        //randomCard = findViewById(R.id.randomCard)

        val r = Random()

       randomCard.setOnClickListener{
           //randomCard.setImageResource(images[r.next(images)])
        }
    }

    fun moveCard(view: View) {
        //View.animate().translationX(400).withLayer()
    }

}
