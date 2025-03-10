package com.shapacreations.generatorfifa22

import androidx.annotation.DrawableRes


var gameId = GameId.FC25.ordinal
var clubList:List<ClubModel> = listOf()
var countryListForSpinner:List<ItemForSpinner> = listOf()
val countryListForSpinnerSex:ArrayList<ItemForSpinner> = arrayListOf()


val gameIconsForSpinner:List<GameIconForSpinner> = listOf(
    GameIconForSpinner(R.drawable.fc25_icon),
    GameIconForSpinner(R.drawable.fc24_icon),
    GameIconForSpinner(R.drawable.fifa23_icon),
    GameIconForSpinner(R.drawable.fifa22_icon)
)

val clubsJsonFile = mapOf(
    GameId.FC24.ordinal to R.string.clubs24_json,
    GameId.FIFA23.ordinal to R.string.clubs23_json,
    GameId.FIFA22.ordinal to R.string.clubs22_json,
    GameId.FC25.ordinal to R.string.clubs25_json
).getOrDefault(gameId, R.string.clubs25_json)

var countryListForSpinnerFifa22:List<ItemForSpinner> = listOf()
var countryListForSpinnerFifa23:List<ItemForSpinner> = listOf()
var countryListForSpinnerFifa24:List<ItemForSpinner> = listOf()
var countryListForSpinnerFifa25:List<ItemForSpinner> = listOf()


data class RandClubs(val firstClub: Int, val secondClub: Int)
data class ClubModel(val name:String, @DrawableRes val logoId:Int, val country:String, val division:String, val strength:Double, val sex:String)
data class ItemForSpinner(@DrawableRes val icon:Int, val text: String, val female:Boolean)
data class GameIconForSpinner(@DrawableRes val icon:Int)

enum class GameId{ FC25,FC24,FIFA23,FIFA22 }
enum class StrengthValue(val value:Double){
    ZERO(0.0),
    HALF(0.5),
    ONE(1.0),
    ONE_AND_HALF(1.5),
    TWO(2.0),
    TWO_AND_HALF(2.5),
    THREE(3.0),
    THREE_AND_HALF(3.5),
    FOUR(4.0),
    FOUR_AND_HALF(4.5),
    FIVE (5.0)}



object GetActivity { var activity: MainActivity? = null }

