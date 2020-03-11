package fr.isen.myapplication.pmu

import androidx.annotation.DrawableRes
import fr.isen.myapplication.R

class Card(var color: CardColor, var value: String){

}
enum class CardColor(@DrawableRes val value: Int,index: Int ) {
    BANANA(R.drawable.realbanana,1),
    STRAWBERRY(R.drawable.realstrawberry,0),
    LEMON(R.drawable.reallemon,2),
    WATERMELON(R.drawable.realwatermelon,3)
}