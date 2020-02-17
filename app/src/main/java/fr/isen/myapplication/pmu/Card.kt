package fr.isen.myapplication.pmu

import androidx.annotation.DrawableRes
import fr.isen.myapplication.R

class Card(var color: CardColor, var value: String){

}
enum class CardColor(@DrawableRes val value: Int ) {
    BANANA(R.drawable.realBanana),
    STRAWBERRY(R.drawable.realStrawberry),
    LEMON(R.drawable.realLemon),
    WATERMELON(R.drawable.realWaterMelon)
}