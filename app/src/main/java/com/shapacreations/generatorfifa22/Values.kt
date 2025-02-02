package com.shapacreations.generatorfifa22


var gameLogo = R.drawable.fc25_icon
var gameId = GameId.FC25.ordinal
var clubList:List<ClubModel> = listOf()
var countryListForSpinner:List<ItemForSpinner> = listOf()


val countryListForSpinnerFifa22:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries"),
    ItemForSpinner(R.drawable.country_1,"Argentina"),
    ItemForSpinner(R.drawable.country_2,"Australia"),
    ItemForSpinner(R.drawable.country_3,"Austria"),
    ItemForSpinner(R.drawable.country_4,"Belgium"),
    ItemForSpinner(R.drawable.country_5,"Brazil"),
    ItemForSpinner(R.drawable.country_6,"China"),
    ItemForSpinner(R.drawable.country_8,"Denmark"),
    ItemForSpinner(R.drawable.country_9,"England"),
    ItemForSpinner(R.drawable.country_10,"France"),
    ItemForSpinner(R.drawable.country_11,"Germany"),
    ItemForSpinner(R.drawable.country_12,"Holland"),
    ItemForSpinner(R.drawable.country_13,"India"),
    ItemForSpinner(R.drawable.country_14,"International"),
    ItemForSpinner(R.drawable.country_15,"Italy"),
    ItemForSpinner(R.drawable.country_16,"Japan"),
    ItemForSpinner(R.drawable.country_26,"South Korea"),
    ItemForSpinner(R.drawable.country_17,"Mexico"),
    ItemForSpinner(R.drawable.country_18,"Norway"),
    ItemForSpinner(R.drawable.country_19,"Poland"),
    ItemForSpinner(R.drawable.country_20,"Portugal"),
    ItemForSpinner(R.drawable.country_21,"Rep.Ireland "),
    ItemForSpinner(R.drawable.country_22,"Rest of World"),
    ItemForSpinner(R.drawable.country_23,"Romania"),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia"),
    ItemForSpinner(R.drawable.country_25,"Scotland"),
    ItemForSpinner(R.drawable.country_27,"Spain"),
    ItemForSpinner(R.drawable.country_28,"Sweden"),
    ItemForSpinner(R.drawable.country_29,"Switzerland"),
    ItemForSpinner(R.drawable.country_30,"Turkey"),
    ItemForSpinner(R.drawable.country_31,"USA")

)
val countryListForSpinnerFifa23:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries"),
    ItemForSpinner(R.drawable.country_1,"Argentina"),
    ItemForSpinner(R.drawable.country_2,"Australia"),
    ItemForSpinner(R.drawable.country_3,"Austria"),
    ItemForSpinner(R.drawable.country_4,"Belgium"),
    ItemForSpinner(R.drawable.country_6,"China"),
    ItemForSpinner(R.drawable.country_8,"Denmark"),
    ItemForSpinner(R.drawable.country_9,"England"),
    ItemForSpinner(R.drawable.country_10,"France"),
    ItemForSpinner(R.drawable.country_11,"Germany"),
    ItemForSpinner(R.drawable.country_13,"India"),
    ItemForSpinner(R.drawable.country_14,"International"),
    ItemForSpinner(R.drawable.country_21,"Rep.Ireland "),
    ItemForSpinner(R.drawable.country_15,"Italy"),
    ItemForSpinner(R.drawable.country_12,"Holland"),
    ItemForSpinner(R.drawable.country_18,"Norway"),
    ItemForSpinner(R.drawable.country_19,"Poland"),
    ItemForSpinner(R.drawable.country_20,"Portugal"),
    ItemForSpinner(R.drawable.country_22,"Rest of World"),
    ItemForSpinner(R.drawable.country_23,"Romania"),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia"),
    ItemForSpinner(R.drawable.country_25,"Scotland"),
    ItemForSpinner(R.drawable.country_26,"South Korea"),
    ItemForSpinner(R.drawable.country_27,"Spain"),
    ItemForSpinner(R.drawable.country_28,"Sweden"),
    ItemForSpinner(R.drawable.country_29,"Switzerland"),
    ItemForSpinner(R.drawable.country_30,"Turkey"),
    ItemForSpinner(R.drawable.country_31,"USA"),

    )
val countryListForSpinnerFifa24:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries"),
    ItemForSpinner(R.drawable.country_1,"Argentina"),
    ItemForSpinner(R.drawable.country_2,"Australia"),
    ItemForSpinner(R.drawable.country_3,"Austria"),
    ItemForSpinner(R.drawable.country_4,"Belgium"),
    ItemForSpinner(R.drawable.country_6,"China"),
    ItemForSpinner(R.drawable.country_8,"Denmark"),
    ItemForSpinner(R.drawable.country_9,"England"),
    ItemForSpinner(R.drawable.country_10,"France"),
    ItemForSpinner(R.drawable.country_11,"Germany"),
    ItemForSpinner(R.drawable.country_12,"Holland"),
    ItemForSpinner(R.drawable.country_13,"India"),
    ItemForSpinner(R.drawable.country_14,"International"),
    ItemForSpinner(R.drawable.country_15,"Italy"),
    ItemForSpinner(R.drawable.country_26,"South Korea"),
    ItemForSpinner(R.drawable.country_18,"Norway"),
    ItemForSpinner(R.drawable.country_19,"Poland"),
    ItemForSpinner(R.drawable.country_20,"Portugal"),
    ItemForSpinner(R.drawable.country_21,"Rep.Ireland "),
    ItemForSpinner(R.drawable.country_22,"Rest of World"),
    ItemForSpinner(R.drawable.country_23,"Romania"),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia"),
    ItemForSpinner(R.drawable.country_25,"Scotland"),
    ItemForSpinner(R.drawable.country_27,"Spain"),
    ItemForSpinner(R.drawable.country_28,"Sweden"),
    ItemForSpinner(R.drawable.country_29,"Switzerland"),
    ItemForSpinner(R.drawable.country_30,"Turkey"),
    ItemForSpinner(R.drawable.country_31,"USA")
)
val countryListForSpinnerFifa25:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries"),
    ItemForSpinner(R.drawable.country_1,"Argentina"),
    ItemForSpinner(R.drawable.country_2,"Australia"),
    ItemForSpinner(R.drawable.country_3,"Austria"),
    ItemForSpinner(R.drawable.country_4,"Belgium"),
    ItemForSpinner(R.drawable.country_6,"China"),
    ItemForSpinner(R.drawable.country_8,"Denmark"),
    ItemForSpinner(R.drawable.country_9,"England"),
    ItemForSpinner(R.drawable.country_10,"France"),
    ItemForSpinner(R.drawable.country_11,"Germany"),
    ItemForSpinner(R.drawable.country_12,"Holland"),
    ItemForSpinner(R.drawable.country_13,"India"),
    ItemForSpinner(R.drawable.country_14,"International"),
    ItemForSpinner(R.drawable.country_21,"Ireland "),
    ItemForSpinner(R.drawable.country_15,"Italy"),
    ItemForSpinner(R.drawable.country_18,"Norway"),
    ItemForSpinner(R.drawable.country_19,"Poland"),
    ItemForSpinner(R.drawable.country_20,"Portugal"),
    ItemForSpinner(R.drawable.country_22,"Rest of World"),
    ItemForSpinner(R.drawable.country_23,"Romania"),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia"),
    ItemForSpinner(R.drawable.country_25,"Scotland"),
    ItemForSpinner(R.drawable.country_26,"South Korea"),
    ItemForSpinner(R.drawable.country_27,"Spain"),
    ItemForSpinner(R.drawable.country_28,"Sweden"),
    ItemForSpinner(R.drawable.country_29,"Switzerland"),
    ItemForSpinner(R.drawable.country_30,"Turkey"),
    ItemForSpinner(R.drawable.country_31,"USA")
)


data class RandClubs(val firstClub: Int, val secondClub: Int)
data class ClubModel(val name:String, val logoId:Int, val country:String, val division:String, val strength:Double, val sex:String)
data class ItemForSpinner(val icon:Int, val text: String)

enum class GameId{ FIFA22, FIFA23, FC24, FC25 }
enum class StrengthValue(val value:Double){
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

