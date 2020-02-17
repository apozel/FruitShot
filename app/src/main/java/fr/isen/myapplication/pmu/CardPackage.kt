package fr.isen.myapplication.pmu

class CardPackage {

    fun getNewCardPackage(): ArrayList<Card>{
        var listOfCardColor: Array<CardColor> =
            arrayOf<CardColor>(CardColor.STRAWBERRY, CardColor.WATERMELON, CardColor.LEMON, CardColor.BANANA)
        for (color in listOfCardColor) {
            for (i in 1..10) {
                cardList.add(Card(color, "${i}"))
            }
            cardList.add(Card(color,"J"))
            cardList.add(Card(color,"Q"))
            cardList.add(Card(color,"R"))
        }
        return cardList
    }

    companion object {
        var cardList: ArrayList<Card> = arrayListOf()
    }
}