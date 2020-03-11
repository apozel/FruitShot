package fr.isen.myapplication.pmu

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import fr.isen.myapplication.R
import kotlinx.android.synthetic.main.activity_pmu.*
import java.security.AccessController.getContext


class PmuActivity : AppCompatActivity() {

    val animator = ValueAnimator.ofFloat(0f, 500f)
    var cardPackage = CardPackage()
    var startPosition: Float = 1.0f
    var startView: ConstraintSet = ConstraintSet()
    var horses: ArrayList<HorseRider> = ArrayList<HorseRider>()
    var nbWinCard: Int = 0
    var position: IntArray = intArrayOf(0, 0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pmu)

        changeCardValue(cardPackage.getRandomCard())

        createHorseRiders()



        cardLayout.setOnClickListener {

            moveFruitForwardFromCard(takeRandomCard())
            surveillanceJeu()

        }


        returnButton.setOnClickListener {
            resetGame()
        }


    }

    fun createHorseRiders() {

        startView.clone(parentView)

        this.horses.add(HorseRider(CardColor.STRAWBERRY, this.startPosition))
        this.horses.add(HorseRider(CardColor.BANANA, this.startPosition))
        this.horses.add(HorseRider(CardColor.LEMON, this.startPosition))
        this.horses.add(HorseRider(CardColor.WATERMELON, this.startPosition))

        horses[0].setStartLayout(couloir1)
        horses[1].setStartLayout(couloir2)
        horses[2].setStartLayout(couloir3)
        horses[3].setStartLayout(couloir4)

    }

    fun surveillanceJeu() {

        for (horse in horses) {

            if (horse.position == 7) {
                deplacementCarte()

            }
            if (!horse.asFinish) {//TODO: finir la partie pour le dernier cavalier
                if (horse.position == 10) {
                    win(horse)
                }
            }
        }
        if (nbWinCard == 4) {
            resetGame()
        }
        if (nbWinCard == 3) {
            for (horse in horses) {
                if (!horse.asFinish) {
                    win(horse)
                }
            }

        }
        if (cardPackage.isFinish()) {
            resetGame()
        }

    }

    private fun win(horse: HorseRider) {
        nbWinCard++
        horse.finishLine(nbWinCard)
        chooseLineChangeColor(horse)

        when (horse.idColor) {
            CardColor.STRAWBERRY -> {
                moveFruitFinishLine(strawberryImage, horses[0], couloir1)
            }
            CardColor.BANANA -> {
                moveFruitFinishLine(bananaImage, horses[1], couloir2)
            }
            CardColor.LEMON -> {
                moveFruitFinishLine(lemonImage, horses[2], couloir3)
            }
            CardColor.WATERMELON -> {
                moveFruitFinishLine(watermelonImage, horses[3], couloir4)
            }
        }
    }

    fun chooseLineChangeColor(horseRider: HorseRider) {
        when (horseRider.idColor) {
            CardColor.STRAWBERRY -> changeColorLine(podiumLine1, horses[0], couloir1)
            CardColor.BANANA -> changeColorLine(podiumLine2, horses[1], couloir2)
            CardColor.LEMON -> changeColorLine(podiumLine3, horses[2], couloir3)
            CardColor.WATERMELON -> changeColorLine(podiumLine4, horses[3], couloir4)
        }
    }

    fun changeColorLine(
        textPodium: TextView,
        horseRider: HorseRider,
        lineLayout: ConstraintLayout
    ) {
        when (horseRider.result) {
            0 -> {
                lineLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.playColor))
                textPodium.visibility = View.INVISIBLE
            }
            1 -> {
                lineLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.firstPlace))
                textPodium.setText(R.string.firstPlace)
                textPodium.visibility = View.VISIBLE
            }
            2 -> {
                lineLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.secondPlace))
                textPodium.setText(R.string.secondPlace)
                textPodium.visibility = View.VISIBLE
            }
            3 -> {
                lineLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.thirdPlace))
                textPodium.setText(R.string.thirdPlace)
                textPodium.visibility = View.VISIBLE
            }
            4 -> {
                lineLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.fourthPlace))
                textPodium.setText(R.string.fourthPlace)
                textPodium.visibility = View.VISIBLE
            }
        }

    }

    fun deplacementCarte() {
        (cardLayout.layoutParams as ConstraintLayout.LayoutParams).verticalBias = 0.9F
    }


    fun takeRandomCard(): Card {
        return cardPackage.getRandomCard()
    }


    fun changeCardValue(card: Card) {
        valueCardView1.setText(card.value)
        valueCardView2.setText(card.value)
        colorCardImage.setImageResource(card.color.value)
    }

    fun moveFruitForwardFromCard(card: Card) {
        changeCardValue(card)
        when (card.color) {
            CardColor.STRAWBERRY -> {
                horses[0].moveForward()
                moveFruit(strawberryImage, horses[0], couloir1)
            }
            CardColor.BANANA -> {
                horses[1].moveForward()
                moveFruit(bananaImage, horses[1], couloir2)
            }
            CardColor.LEMON -> {
                horses[2].moveForward()
                moveFruit(lemonImage, horses[2], couloir3)
            }
            CardColor.WATERMELON -> {
                horses[3].moveForward()
                moveFruit(watermelonImage, horses[3], couloir4)
            }
        }
    }

    fun moveFruitFinishLine(view: View, horseRider: HorseRider, line: ConstraintLayout) {
        horseRider.currentLayout.clone(line)
        horseRider.currentLayout.setVerticalBias(view.id, 0.5f)
        TransitionManager.beginDelayedTransition(line)
        horseRider.currentLayout.applyTo(line)
        Log.d(
            "position",
            "position cav : " + horseRider.position + " horse bias :" + horseRider.getBiasPosition()
        )
    }

    fun moveFruit(view: View, horseRider: HorseRider, line: ConstraintLayout) {
        horseRider.currentLayout.clone(line)
        horseRider.currentLayout.setVerticalBias(view.id, horseRider.getBiasPosition())
        TransitionManager.beginDelayedTransition(line)
        horseRider.currentLayout.applyTo(line)
        Log.d(
            "position",
            "position cav : " + horseRider.position + " horse bias :" + horseRider.getBiasPosition()
        )
    }

    fun moveResetFruit(view: View, horseRider: HorseRider, line: ConstraintLayout) {

        TransitionManager.beginDelayedTransition(line)
        horseRider.startLayout.applyTo(line)
        Log.d(
            "position",
            "position reset cav : " + horseRider.position + " horse bias :" + horseRider.getBiasPosition()
        )
    }

    fun moveResetCard() {
        for (horse in horses) {

            when (horse.idColor) {
                CardColor.STRAWBERRY -> {
                    moveResetFruit(strawberryImage, horses[0], couloir1)
                }
                CardColor.BANANA -> {
                    moveResetFruit(bananaImage, horses[1], couloir2)
                }
                CardColor.LEMON -> {
                    moveResetFruit(lemonImage, horses[2], couloir3)
                }
                CardColor.WATERMELON -> {
                    moveResetFruit(watermelonImage, horses[3], couloir4)
                }
            }

        }
    }

    fun resetGame() {
        val intent = Intent(this,PmuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }




}
