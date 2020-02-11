package fr.isen.myapplication.pmu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.myapplication.R
import kotlinx.android.synthetic.main.activity_pmu.*

class PmuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pmu)
        changeCardValue(Card(CardColor.STRAWBERRY,"K"))
    }

    fun changeCardValue(card: Card){
        valueCardView1.setText(card.value)
        valueCardView2.setText(card.value)
        colorCardImage.setImageResource(card.color.value)
    }
}
