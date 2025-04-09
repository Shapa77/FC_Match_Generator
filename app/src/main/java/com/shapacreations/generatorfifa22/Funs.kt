package com.shapacreations.generatorfifa22

import android.content.Context
import android.content.res.Configuration
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.JsonParser
import java.util.Locale
import kotlin.random.Random


fun rand(selectedClubsList:MutableList<ClubModel>):Int{ return if (selectedClubsList.isNotEmpty()) Random.nextInt(selectedClubsList.size) else 0 }

fun changeFragment(newFragment: Fragment, gameIdLocal:Int, addToBackStack:Boolean) {
    val transaction = GetActivity.activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.FragmentContainer, newFragment)
    if (addToBackStack) transaction?.addToBackStack(null)
    transaction?.commit()
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
fun initList(context: Context, selectedClubsList: MutableList<ClubModel>, selectedCountryForFilter: String, selectedDivisionForFilter: String, minimumStrengthForFilter: Double, maximumStrengthForFilter: Double, selectedSexForFilter: String) {
    val allCountries = getStandardStringByValue(context, context.getString(R.string.All_countries))
    val allDivisions = getStandardStringByValue(context, context.getString(R.string.All_divisions))
    val allSex = context.getString(R.string.All_sex)

    for (club in clubList) {
        val matchesCountry = selectedCountryForFilter == allCountries || club.country == selectedCountryForFilter
        val matchesDivision = selectedDivisionForFilter == allDivisions || club.division == selectedDivisionForFilter
        val matchesSex = selectedSexForFilter == allSex || club.sex == selectedSexForFilter
        val matchesStrength = club.strength in minimumStrengthForFilter..maximumStrengthForFilter

        if (matchesCountry && matchesDivision && matchesSex && matchesStrength) {
            selectedClubsList.add(club)
        }
    }
}

fun strengthStarSet(binding: ViewBinding, strength: Double, strengthStarsList: List<String>) {
    val stars = strengthStarsList.map {
        val field = binding::class.java.getDeclaredField(it)
        field.isAccessible = true
        field.get(binding) as ImageView
    }

    // Масив ресурсів для зірок
    val starImages = when (strength) {
        StrengthValue.ZERO.value -> listOf(R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.HALF.value -> listOf(R.drawable.star_half_full, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.ONE.value -> listOf(R.drawable.star_full, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.ONE_AND_HALF.value -> listOf(R.drawable.star_full, R.drawable.star_half_full, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.TWO.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.TWO_AND_HALF.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_half_full, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.THREE.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_empty, R.drawable.star_empty)
        StrengthValue.THREE_AND_HALF.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_half_full, R.drawable.star_empty)
        StrengthValue.FOUR.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_empty)
        StrengthValue.FOUR_AND_HALF.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_half_full)
        StrengthValue.FIVE.value -> listOf(R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_full, R.drawable.star_full)
        else -> listOf(R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty, R.drawable.star_empty)
    }

    // Встановлюємо зображення для кожної зірки
    stars.forEachIndexed { index, starImage ->
        starImage.setImageResource(starImages[index])
    }
}

fun getStandardStringByValue(context: Context, value: String): String {
    val fields = R.string::class.java.fields
    var key = ""

    for (field in fields) {
        val resId = field.getInt(null)
        val resValue = context.getString(resId)
        if (resValue == value) {
            key = field.name
            break
        }
    }

    val defaultLocale = Locale.ENGLISH
    val config = Configuration(context.resources.configuration)
    config.setLocale(defaultLocale)
    val defaultContext = context.createConfigurationContext(config)
    val resId = defaultContext.resources.getIdentifier(key, "string", context.packageName)

    return if (resId != 0) {
        defaultContext.resources.getString(resId)
    } else {
        value
    }
}

fun getLocalizedStringByValue(context: Context, standardValue: String): String {
    val defaultLocale = Locale.ENGLISH
    val config = Configuration(context.resources.configuration)
    config.setLocale(defaultLocale)
    val defaultContext = context.createConfigurationContext(config)

    val fields = R.string::class.java.fields
    var key = ""
    for (field in fields) {
        val resId = field.getInt(null)
        val resValue = defaultContext.getString(resId)
        if (resValue == standardValue) {
            key = field.name
            break
        }
    }

    val resId = context.resources.getIdentifier(key, "string", context.packageName)
    return if (resId != 0) {
        context.resources.getString(resId)
    } else {
        standardValue
    }
}

fun getStrengthValue(clubStrength: Double): Double {
    return when (clubStrength) {
        StrengthValue.HALF.value -> StrengthValue.HALF.value
        StrengthValue.ONE.value -> StrengthValue.ONE.value
        StrengthValue.ONE_AND_HALF.value -> StrengthValue.ONE_AND_HALF.value
        StrengthValue.TWO.value -> StrengthValue.TWO.value
        StrengthValue.TWO_AND_HALF.value -> StrengthValue.TWO_AND_HALF.value
        StrengthValue.THREE.value -> StrengthValue.THREE.value
        StrengthValue.THREE_AND_HALF.value -> StrengthValue.THREE_AND_HALF.value
        StrengthValue.FOUR.value -> StrengthValue.FOUR.value
        StrengthValue.FOUR_AND_HALF.value -> StrengthValue.FOUR_AND_HALF.value
        StrengthValue.FIVE.value -> StrengthValue.FIVE.value
        else -> clubStrength
    }
}

fun resetClubInfo(logo: ImageView, name: TextView, division: TextView, country: TextView) {
    logo.setImageResource(R.drawable.club_default)
    name.text = null
    division.text = null
    country.text = null
}

fun showToast(context: Context, messageId: Int) { Toast.makeText(context, context.getString(messageId), Toast.LENGTH_SHORT).show() }







