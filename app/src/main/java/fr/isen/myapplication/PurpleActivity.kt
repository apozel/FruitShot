package fr.isen.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.myapplication.pmu.Card
import fr.isen.myapplication.pmu.CardColor
import kotlinx.android.synthetic.main.activity_pmu.*
import kotlinx.android.synthetic.main.activity_purple.*

class PurpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purple)
        changeCardValue(Card(CardColor.STRAWBERRY,"K"))
    }
    fun changeCardValue(card: Card){
        textCard1.setText(card.value)
        textCard2.setText(card.value)
        cardView.setImageResource(card.color.value)
    }
}
