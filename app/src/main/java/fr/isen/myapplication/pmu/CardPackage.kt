package fr.isen.myapplication.pmu

class CardPackage {
    init {
        var listOfCardColor: Array<CardColor> =
            arrayOf<CardColor>(CardColor.STRAWBERRY, CardColor.WATERMELON, CardColor.LEMON, CardColor.BANANA)
        for (color in listOfCardColor) {
            for (i in 0..10) {
                cardList.add(Card(color, "${i}"))
            }
            cardList.add(Card(color,"J"))
            cardList.add(Card(color,"Q"))
            cardList.add(Card(color,"R"))
        }

    }

    companion object {
        var cardList: ArrayList<Card> = arrayListOf()
    }
}