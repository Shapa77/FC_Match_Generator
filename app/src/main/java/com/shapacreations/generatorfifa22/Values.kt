package com.shapacreations.generatorfifa22


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
val countryListForSpinnerFifa22:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries",false),
    ItemForSpinner(R.drawable.country_1,"Argentina",false),
    ItemForSpinner(R.drawable.country_2,"Australia",false),
    ItemForSpinner(R.drawable.country_3,"Austria",false),
    ItemForSpinner(R.drawable.country_4,"Belgium",false),
    ItemForSpinner(R.drawable.country_5,"Brazil",false),
    ItemForSpinner(R.drawable.country_6,"China",false),
    ItemForSpinner(R.drawable.country_8,"Denmark",false),
    ItemForSpinner(R.drawable.country_9,"England",false),
    ItemForSpinner(R.drawable.country_10,"France",false),
    ItemForSpinner(R.drawable.country_11,"Germany",false),
    ItemForSpinner(R.drawable.country_12,"Holland",false),
    ItemForSpinner(R.drawable.country_13,"India",false),
    ItemForSpinner(R.drawable.country_14,"International",false),
    ItemForSpinner(R.drawable.country_15,"Italy",false),
    ItemForSpinner(R.drawable.country_16,"Japan",false),
    ItemForSpinner(R.drawable.country_26,"South Korea",false),
    ItemForSpinner(R.drawable.country_17,"Mexico",false),
    ItemForSpinner(R.drawable.country_18,"Norway",false),
    ItemForSpinner(R.drawable.country_19,"Poland",false),
    ItemForSpinner(R.drawable.country_20,"Portugal",false),
    ItemForSpinner(R.drawable.country_21,"Rep.Ireland ",false),
    ItemForSpinner(R.drawable.country_22,"Rest of World",false),
    ItemForSpinner(R.drawable.country_23,"Romania",false),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia",false),
    ItemForSpinner(R.drawable.country_25,"Scotland",false),
    ItemForSpinner(R.drawable.country_27,"Spain",false),
    ItemForSpinner(R.drawable.country_28,"Sweden",false),
    ItemForSpinner(R.drawable.country_29,"Switzerland",false),
    ItemForSpinner(R.drawable.country_30,"Turkey",false),
    ItemForSpinner(R.drawable.country_31,"USA",false)

)
val countryListForSpinnerFifa23:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries",false),
    ItemForSpinner(R.drawable.country_1,"Argentina",false),
    ItemForSpinner(R.drawable.country_2,"Australia",false),
    ItemForSpinner(R.drawable.country_3,"Austria",false),
    ItemForSpinner(R.drawable.country_4,"Belgium",false),
    ItemForSpinner(R.drawable.country_6,"China",false),
    ItemForSpinner(R.drawable.country_8,"Denmark",false),
    ItemForSpinner(R.drawable.country_9,"England",false),
    ItemForSpinner(R.drawable.country_10,"France",false),
    ItemForSpinner(R.drawable.country_11,"Germany",false),
    ItemForSpinner(R.drawable.country_13,"India",false),
    ItemForSpinner(R.drawable.country_14,"International",false),
    ItemForSpinner(R.drawable.country_21,"Rep.Ireland ",false),
    ItemForSpinner(R.drawable.country_15,"Italy",false),
    ItemForSpinner(R.drawable.country_12,"Holland",false),
    ItemForSpinner(R.drawable.country_18,"Norway",false),
    ItemForSpinner(R.drawable.country_19,"Poland",false),
    ItemForSpinner(R.drawable.country_20,"Portugal",false),
    ItemForSpinner(R.drawable.country_22,"Rest of World",false),
    ItemForSpinner(R.drawable.country_23,"Romania",false),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia",false),
    ItemForSpinner(R.drawable.country_25,"Scotland",false),
    ItemForSpinner(R.drawable.country_26,"South Korea",false),
    ItemForSpinner(R.drawable.country_27,"Spain",false),
    ItemForSpinner(R.drawable.country_28,"Sweden",false),
    ItemForSpinner(R.drawable.country_29,"Switzerland",false),
    ItemForSpinner(R.drawable.country_30,"Turkey",false),
    ItemForSpinner(R.drawable.country_31,"USA",false),

    )
val countryListForSpinnerFifa24:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries",true),
    ItemForSpinner(R.drawable.country_1,"Argentina",false),
    ItemForSpinner(R.drawable.country_2,"Australia",false),
    ItemForSpinner(R.drawable.country_3,"Austria",false),
    ItemForSpinner(R.drawable.country_4,"Belgium",false),
    ItemForSpinner(R.drawable.country_6,"China",false),
    ItemForSpinner(R.drawable.country_8,"Denmark",false),
    ItemForSpinner(R.drawable.country_9,"England",true),
    ItemForSpinner(R.drawable.country_10,"France",true),
    ItemForSpinner(R.drawable.country_11,"Germany",true),
    ItemForSpinner(R.drawable.country_12,"Holland",false),
    ItemForSpinner(R.drawable.country_13,"India",false),
    ItemForSpinner(R.drawable.country_14,"International",false),
    ItemForSpinner(R.drawable.country_15,"Italy",false),
    ItemForSpinner(R.drawable.country_26,"South Korea",false),
    ItemForSpinner(R.drawable.country_18,"Norway",false),
    ItemForSpinner(R.drawable.country_19,"Poland",false),
    ItemForSpinner(R.drawable.country_20,"Portugal",false),
    ItemForSpinner(R.drawable.country_21,"Rep.Ireland ",false),
    ItemForSpinner(R.drawable.country_22,"Rest of World",true),
    ItemForSpinner(R.drawable.country_23,"Romania",false),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia",false),
    ItemForSpinner(R.drawable.country_25,"Scotland",false),
    ItemForSpinner(R.drawable.country_27,"Spain",true),
    ItemForSpinner(R.drawable.country_28,"Sweden",false),
    ItemForSpinner(R.drawable.country_29,"Switzerland",false),
    ItemForSpinner(R.drawable.country_30,"Turkey",false),
    ItemForSpinner(R.drawable.country_31,"USA",true)
)
val countryListForSpinnerFifa25:List<ItemForSpinner> = listOf(
    ItemForSpinner(R.drawable.country_14,"All countries",true),
    ItemForSpinner(R.drawable.country_1,"Argentina",false),
    ItemForSpinner(R.drawable.country_2,"Australia",false),
    ItemForSpinner(R.drawable.country_3,"Austria",false),
    ItemForSpinner(R.drawable.country_4,"Belgium",false),
    ItemForSpinner(R.drawable.country_6,"China",false),
    ItemForSpinner(R.drawable.country_8,"Denmark",false),
    ItemForSpinner(R.drawable.country_9,"England",true),
    ItemForSpinner(R.drawable.country_10,"France",true),
    ItemForSpinner(R.drawable.country_11,"Germany",true),
    ItemForSpinner(R.drawable.country_12,"Holland",false),
    ItemForSpinner(R.drawable.country_13,"India",false),
    ItemForSpinner(R.drawable.country_14,"International",true),
    ItemForSpinner(R.drawable.country_21,"Ireland ",false),
    ItemForSpinner(R.drawable.country_15,"Italy",false),
    ItemForSpinner(R.drawable.country_18,"Norway",false),
    ItemForSpinner(R.drawable.country_19,"Poland",false),
    ItemForSpinner(R.drawable.country_20,"Portugal",false),
    ItemForSpinner(R.drawable.country_22,"Rest of World",true),
    ItemForSpinner(R.drawable.country_23,"Romania",false),
    ItemForSpinner(R.drawable.country_24,"Saudi Arabia",false),
    ItemForSpinner(R.drawable.country_25,"Scotland",false),
    ItemForSpinner(R.drawable.country_26,"South Korea",false),
    ItemForSpinner(R.drawable.country_27,"Spain",true),
    ItemForSpinner(R.drawable.country_28,"Sweden",false),
    ItemForSpinner(R.drawable.country_29,"Switzerland",false),
    ItemForSpinner(R.drawable.country_30,"Turkey",false),
    ItemForSpinner(R.drawable.country_31,"USA",true)
)


data class RandClubs(val firstClub: Int, val secondClub: Int)
data class ClubModel(val name:String, val logoId:Int, val country:String, val division:String, val strength:Double, val sex:String)
data class ItemForSpinner(val icon:Int, val text: String, val female:Boolean)
data class GameIconForSpinner(val icon:Int)

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

