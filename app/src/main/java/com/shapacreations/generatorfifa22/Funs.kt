package com.shapacreations.generatorfifa22

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.JsonParser
import kotlin.random.Random


fun rand(selectedClubsList:MutableList<ClubModel>):Int{
    var randomClubIndex = 0
    if (selectedClubsList.size>0){ randomClubIndex =  Random.nextInt(selectedClubsList.size) }
    return randomClubIndex
}
fun changeFragment(newFragment: Fragment, gameIdLocal:Int, addToBackStack:Boolean) {
    if(!addToBackStack) GetActivity.activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.FragmentContainer, newFragment)?.commit()
    else GetActivity.activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.FragmentContainer, newFragment)?.addToBackStack(null)?.commit()
    gameId = gameIdLocal
}
fun loadClubsFromJson(context: Context, fileName:String): List<ClubModel> {
    val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    val jsonArray = JsonParser.parseString(jsonString).asJsonArray
    return jsonArray.map { jsonElement ->
        val jsonObject = jsonElement.asJsonObject
        val name = jsonObject.get(context.getString(R.string.name)).asString
        val logoId = jsonObject.get(context.getString(R.string.logoId)).asString
        val logoIdInt = context.resources.getIdentifier(logoId, context.getString(R.string.drawable), context.packageName)
        val country = jsonObject.get(context.getString(R.string.country)).asString
        val division = jsonObject.get(context.getString(R.string.division)).asString
        val strength = jsonObject.get(context.getString(R.string.strength)).asDouble
        val sex = jsonObject.get(context.getString(R.string.sex)).asString

        ClubModel(name, logoIdInt, country, division, strength, sex)
    }
}
fun initList(context:Context,selectedClubsList:MutableList<ClubModel>,selectedCountryForFilter:String,selectedDivisionForFilter:String,minimumStrengthForFilter:Double,maximumStrengthForFilter:Double,selectedSexForFilter:String){

    if(selectedCountryForFilter == context.getString(R.string.All_countries) && selectedDivisionForFilter == context.getString(R.string.All_divisions) && selectedSexForFilter == context.getString(R.string.All_sex)){
        for(i in clubList.indices) {
            if(clubList[i].strength in minimumStrengthForFilter..maximumStrengthForFilter) {
                selectedClubsList.add(clubList[i])
            }
        }
    }
    else if(selectedCountryForFilter == context.getString(R.string.All_countries) && selectedDivisionForFilter == context.getString(R.string.All_divisions) && selectedSexForFilter != context.getString(R.string.All_sex)){
        for(i in clubList.indices) {
            if(clubList[i].strength in minimumStrengthForFilter..maximumStrengthForFilter && clubList[i].sex == selectedSexForFilter) {
                selectedClubsList.add(clubList[i])
            }
        }
    }
    else if(selectedCountryForFilter != context.getString(R.string.All_countries) && selectedDivisionForFilter == context.getString(R.string.All_divisions) && selectedSexForFilter == context.getString(R.string.All_sex)){
        for(i in clubList.indices) {
            if(clubList[i].country == selectedCountryForFilter && clubList[i].strength in minimumStrengthForFilter..maximumStrengthForFilter){
                selectedClubsList.add(clubList[i])
            }
        }
    }
    else if(selectedCountryForFilter != context.getString(R.string.All_countries) && selectedDivisionForFilter == context.getString(R.string.All_divisions) && selectedSexForFilter != context.getString(R.string.All_sex)){
        for(i in clubList.indices) {
            if(clubList[i].country == selectedCountryForFilter && clubList[i].strength in minimumStrengthForFilter..maximumStrengthForFilter && clubList[i].sex == selectedSexForFilter){
                selectedClubsList.add(clubList[i])
            }
        }
    }

    else{
        for(i in clubList.indices) {
            if(clubList[i].country == selectedCountryForFilter && clubList[i].division == selectedDivisionForFilter && clubList[i].strength in minimumStrengthForFilter..maximumStrengthForFilter){
                selectedClubsList.add(clubList[i])
            }
        }
    }
}
fun strengthStarSet(binding:ViewBinding,strength:Double, star1:String, star2:String, star3:String, star4:String, star5:String) {

    val star1ID = binding::class.java.getDeclaredField(star1)
    val star2ID = binding::class.java.getDeclaredField(star2)
    val star3ID = binding::class.java.getDeclaredField(star3)
    val star4ID = binding::class.java.getDeclaredField(star4)
    val star5ID = binding::class.java.getDeclaredField(star5)

    star1ID.isAccessible = true
    star2ID.isAccessible = true
    star3ID.isAccessible = true
    star4ID.isAccessible = true
    star5ID.isAccessible = true

    val imageStar1 = star1ID.get(binding) as ImageView
    val imageStar2 = star2ID.get(binding) as ImageView
    val imageStar3 = star3ID.get(binding) as ImageView
    val imageStar4 = star4ID.get(binding) as ImageView
    val imageStar5 = star5ID.get(binding) as ImageView

    when (strength) {
        StrengthValue.ZERO.value -> {
            imageStar1.setImageResource(R.drawable.star_empty)
            imageStar2.setImageResource(R.drawable.star_empty)
            imageStar3.setImageResource(R.drawable.star_empty)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }
        StrengthValue.HALF.value -> {
            imageStar1.setImageResource(R.drawable.star_half_full)
            imageStar2.setImageResource(R.drawable.star_empty)
            imageStar3.setImageResource(R.drawable.star_empty)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.ONE.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_empty)
            imageStar3.setImageResource(R.drawable.star_empty)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.ONE_AND_HALF.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_half_full)
            imageStar3.setImageResource(R.drawable.star_empty)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.TWO.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_empty)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.TWO_AND_HALF.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_half_full)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.THREE.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_full)
            imageStar4.setImageResource(R.drawable.star_empty)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.THREE_AND_HALF.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_full)
            imageStar4.setImageResource(R.drawable.star_half_full)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.FOUR.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_full)
            imageStar4.setImageResource(R.drawable.star_full)
            imageStar5.setImageResource(R.drawable.star_empty)
        }

        StrengthValue.FOUR_AND_HALF.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_full)
            imageStar4.setImageResource(R.drawable.star_full)
            imageStar5.setImageResource(R.drawable.star_half_full)
        }

        StrengthValue.FIVE.value -> {
            imageStar1.setImageResource(R.drawable.star_full)
            imageStar2.setImageResource(R.drawable.star_full)
            imageStar3.setImageResource(R.drawable.star_full)
            imageStar4.setImageResource(R.drawable.star_full)
            imageStar5.setImageResource(R.drawable.star_full)
        }
    }
}




