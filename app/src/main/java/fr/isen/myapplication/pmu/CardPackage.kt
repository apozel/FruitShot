package fr.isen.myapplication.pmu

import kotlinx.android.synthetic.main.activity_pmu.*
import kotlin.random.Random

class CardPackage {
private var cardPackage: ArrayList<Card> = getNewCardPackage()
    fun getNewCardPackage(): ArrayList<Card> {
        var listOfCardColor: Array<CardColor> =
            arrayOf<CardColor>(
                CardColor.STRAWBERRY,
                CardColor.WATERMELON,
                CardColor.LEMON,
                CardColor.BANANA
            )
        for (color in listOfCardColor) {
            for (i in 1..10) {
                cardList.add(Card(color, "${i}"))
            }
            cardList.add(Card(color, "J"))
            cardList.add(Card(color, "Q"))
            cardList.add(Card(color, "R"))
        }
        return cardList
    }

    fun getRandomCard(): Card {

        var carte = cardList.get(Random.nextInt(0, cardList.size))
        cardList.remove(carte)

        return carte

    }

    fun isFinish():Boolean{
        if (cardPackage.size == 0)
            return true
        return false
    }

    fun mixPackage(){
        this.cardPackage.clear()
        this.cardPackage = getNewCardPackage()
    }

    companion object {
        var cardList: ArrayList<Card> = arrayListOf()
    }
}