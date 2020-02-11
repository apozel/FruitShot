package fr.isen.myapplication.pmu

import androidx.annotation.DrawableRes
import fr.isen.myapplication.R

class Card(var color: CardColor, var value: String){

}
enum class CardColor(@DrawableRes val value: Int ) {
    BANANA(R.drawable.banana),
    STRAWBERRY(R.drawable.strawberry),
    LEMON(R.drawable.lemon),
    WATERMELON(R.drawable.watermelon)
}