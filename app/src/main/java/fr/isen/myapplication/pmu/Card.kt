package fr.isen.myapplication.pmu

import androidx.annotation.DrawableRes
import fr.isen.myapplication.R

class Card(var color: CardColor, var value: String){

}
enum class CardColor(@DrawableRes val value: Int ) {
    BANANA(R.drawable.realbanana),
    STRAWBERRY(R.drawable.realstrawberry),
    LEMON(R.drawable.reallemon),
    WATERMELON(R.drawable.realwatermelon)
}