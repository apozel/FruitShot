package fr.isen.myapplication.pmu

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import fr.isen.myapplication.R
import kotlinx.android.synthetic.main.activity_pmu.*
import kotlin.random.Random

class PmuActivity : AppCompatActivity() {

    val animator  = ValueAnimator.ofFloat(0f, 500f)
    var cardPackage = CardPackage().getNewCardPackage()
    var FirstLayout = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pmu)
        changeCardValue(Card(CardColor.STRAWBERRY, "K"))

            FirstLayout.clone(parentView)

        cardLayout.setOnClickListener {
            //moveCardBackward(it)
            resetLayout()
            changeCardValue(takeRandomCard())

        }
        strawberryImage.setOnClickListener{
            moveLayout(it)
        }
        bananaImage.setOnClickListener{
            moveLayout(it)
        }
        lemonImage.setOnClickListener {
            moveLayout(it)
        }
        watermelonImage.setOnClickListener {
            moveLayout(it)
        }


    }

    fun prepareCardPackage(){
        cardPackage = CardPackage.cardList
        for (i in cardPackage){
            if(i.value == "V"){
                cardPackage.remove(i)
            }
        }
    }

    fun takeRandomCard(): Card{
       return cardPackage.get(Random.nextInt(0,cardPackage.size))
    }

    fun returnCard(card: Card){
        //.setImageResource(card.color.value)

    }

    fun changeCardValue(card: Card) {
        valueCardView1.setText(card.value)
        valueCardView2.setText(card.value)
        colorCardImage.setImageResource(card.color.value)
    }



    fun moveLayout(view: View){
        val constraintSet = ConstraintSet()
        constraintSet.clone(parentView)

        constraintSet.setVerticalBias(view.id,90f)
        TransitionManager.beginDelayedTransition(parentView)
        constraintSet.applyTo(parentView)
    }

    fun resetLayout(){
        TransitionManager.beginDelayedTransition(parentView)
        FirstLayout.applyTo(parentView)
    }


}
