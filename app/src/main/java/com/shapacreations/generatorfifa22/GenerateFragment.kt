package com.shapacreations.generatorfifa22

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.shapacreations.generatorfifa22.databinding.FragmentGenerateBinding

class GenerateFragment : Fragment() {

    private val binding by lazy { FragmentGenerateBinding.inflate(layoutInflater) }

    private lateinit var context: Context

    private lateinit var selectedCountryForFilter:String
    private lateinit var selectedDivisionForFilter:String
    private lateinit var selectedSexForFilter:String

    private lateinit var listFirstClubStars:List<String>
    private lateinit var listSecondClubStars:List<String>

    private lateinit var listMinimumStrengthStars:List<String>
    private lateinit var listMaximumStrengthStars:List<String>

    private val divisionListForSpinner:ArrayList<String> = arrayListOf()
    private val selectedClubsList = mutableListOf<ClubModel>()

    private var minStarSet = StrengthValue.HALF.value
    private var maxStarSet = StrengthValue.FIVE.value

    private var minimumStrengthForFilter = StrengthValue.HALF.value
    private var maximumStrengthForFilter = StrengthValue.FIVE.value

    private var minimumStrengthStar1 = false
    private var minimumStrengthStar2 = false
    private var minimumStrengthStar3 = false
    private var minimumStrengthStar4 = false
    private var minimumStrengthStar5 = false

    private var maximumStrengthStar1 = false
    private var maximumStrengthStar2 = false
    private var maximumStrengthStar3 = false
    private var maximumStrengthStar4 = false
    private var maximumStrengthStar5 = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState)

        initValues()
        initSpinners()

        binding.minimumStrengthStar1.setOnClickListener {
            if(!minimumStrengthStar1){ setMinimumStrengthClick(true,StrengthValue.ONE.value,1) }
            else{setMinimumStrengthClick(false,StrengthValue.HALF.value,1) }
        }
        binding.minimumStrengthStar2.setOnClickListener {
            if(!minimumStrengthStar2){ setMinimumStrengthClick(true,StrengthValue.ONE_AND_HALF.value,2) }
            else{setMinimumStrengthClick(false,StrengthValue.TWO.value,2) }
        }
        binding.minimumStrengthStar3.setOnClickListener {
            if(!minimumStrengthStar3){ setMinimumStrengthClick(true,StrengthValue.TWO_AND_HALF.value,3) }
            else{setMinimumStrengthClick(false,StrengthValue.THREE.value,3) }
        }
        binding.minimumStrengthStar4.setOnClickListener {
            if(!minimumStrengthStar4){ setMinimumStrengthClick(true,StrengthValue.THREE_AND_HALF.value,4) }
            else{setMinimumStrengthClick(false,StrengthValue.FOUR.value,4) }
        }
        binding.minimumStrengthStar5.setOnClickListener {
            if(!minimumStrengthStar5){ setMinimumStrengthClick(true,StrengthValue.FOUR_AND_HALF.value,5) }
            else{setMinimumStrengthClick(false,StrengthValue.FIVE.value,5) }
        }

        binding.maximumStrengthStar1.setOnClickListener {
            if(!maximumStrengthStar1){ setMaximumStrengthClick(true,StrengthValue.ONE.value,1) }
            else{ setMaximumStrengthClick(false,StrengthValue.HALF.value,1) }
        }
        binding.maximumStrengthStar2.setOnClickListener {
            if(!maximumStrengthStar2){ setMaximumStrengthClick(true,StrengthValue.ONE_AND_HALF.value,2) }
            else{ setMaximumStrengthClick(false,StrengthValue.TWO.value,2) }
        }
        binding.maximumStrengthStar3.setOnClickListener {
            if(!maximumStrengthStar3){ setMaximumStrengthClick(true,StrengthValue.TWO_AND_HALF.value,3) }
            else{ setMaximumStrengthClick(false,StrengthValue.THREE.value,3) }
        }
        binding.maximumStrengthStar4.setOnClickListener {
            if(!maximumStrengthStar4){ setMaximumStrengthClick(true,StrengthValue.THREE_AND_HALF.value,4) }
            else{ setMaximumStrengthClick(false,StrengthValue.FOUR.value,4) }
        }
        binding.maximumStrengthStar5.setOnClickListener {
            if(!maximumStrengthStar5){ setMaximumStrengthClick(true,StrengthValue.FOUR_AND_HALF.value,5) }
            else{ setMaximumStrengthClick(false,StrengthValue.FIVE.value,5) }
        }

        binding.generateButton.setOnClickListener {

            initList(context,selectedClubsList,selectedCountryForFilter,selectedDivisionForFilter,minimumStrengthForFilter, maximumStrengthForFilter,selectedSexForFilter)
            if (selectedClubsList.size > 0) {
                setClubs(checkClubs(context,rand(selectedClubsList), rand(selectedClubsList)))
                selectedClubsList.clear()
            }
            else {
                Toast.makeText(context,R.string.Not_found,Toast.LENGTH_SHORT).show()
            }
        }

        binding.advFilter.setOnClickListener { changeFragment(AdvancedGenerateFragment(), gameId,false) }
    }

    private fun initValues() {

        countryListForSpinner = when (gameId) {
            GameId.FC24.ordinal -> countryListForSpinnerFifa24
            GameId.FIFA23.ordinal -> countryListForSpinnerFifa23
            GameId.FIFA22.ordinal -> countryListForSpinnerFifa22
            GameId.FC25.ordinal -> countryListForSpinnerFifa25
            else -> countryListForSpinnerFifa25
        }
        clubList = when (gameId) {
            GameId.FC24.ordinal -> loadClubsFromJson(context,getString(R.string.clubs24_json))
            GameId.FIFA23.ordinal -> loadClubsFromJson(context,getString(R.string.clubs23_json))
            GameId.FIFA22.ordinal -> loadClubsFromJson(context,getString(R.string.clubs22_json))
            GameId.FC25.ordinal -> loadClubsFromJson(context,getString(R.string.clubs25_json))
            else -> loadClubsFromJson(context,getString(R.string.clubs25_json))
        }

        selectedSexForFilter = getString(R.string.All_sex)

        listFirstClubStars = listOf(
            getString(R.string.firstClubStar1),
            getString(R.string.firstClubStar2),
            getString(R.string.firstClubStar3),
            getString(R.string.firstClubStar4),
            getString(R.string.firstClubStar5))
        listSecondClubStars = listOf(
            getString(R.string.secondClubStar1),
            getString(R.string.secondClubStar2),
            getString(R.string.secondClubStar3),
            getString(R.string.secondClubStar4),
            getString(R.string.secondClubStar5))

        listMinimumStrengthStars = listOf(
            getString(R.string.minimumStrengthStar1),
            getString(R.string.minimumStrengthStar2),
            getString(R.string.minimumStrengthStar3),
            getString(R.string.minimumStrengthStar4),
            getString(R.string.minimumStrengthStar5))
        listMaximumStrengthStars = listOf(
            getString(R.string.maximumStrengthStar1),
            getString(R.string.maximumStrengthStar2),
            getString(R.string.maximumStrengthStar3),
            getString(R.string.maximumStrengthStar4),
            getString(R.string.maximumStrengthStar5))

    }
    private fun initSpinners(){

        selectedCountryForFilter = getString(R.string.All_countries)
        selectedDivisionForFilter = getString(R.string.All_divisions)

        divisionListForSpinner.add(getString(R.string.All_divisions))

        val spinnerCountryAdapter = CountrySpinnerAdapter(context,countryListForSpinner)
        val spinnerDivisionAdapter = DivisionSpinnerAdapter(context, divisionListForSpinner)
        val spinnerGameAdapter = GameSpinnerAdapter(context, gameIconsForSpinner)

        binding.spinnerCountry.adapter = spinnerCountryAdapter
        binding.spinnerDivision.adapter = spinnerDivisionAdapter
        binding.spinnerGame.adapter = spinnerGameAdapter

        binding.spinnerGame.setSelection(gameId)

        binding.spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectedCountryForFilter = countryListForSpinner[position].text
                selectedDivisionForFilter = getString(R.string.All_divisions)

                divisionListForSpinner.clear()
                divisionListForSpinner.add(getString(R.string.All_divisions))

                for(i in clubList){
                    if(i.country == selectedCountryForFilter && !divisionListForSpinner.contains(i.division)){
                        divisionListForSpinner.add(i.division)
                    }
                }

                binding.spinnerDivision.setSelection(0)
                setStrengthMinMaxStar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
        binding.spinnerDivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedDivisionForFilter = divisionListForSpinner[position]
                setStrengthMinMaxStar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
        binding.spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(gameId != position){
                    gameId = position
                    initValues()
                    initSpinners()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    private fun setClubs(randClubs: RandClubs){

        binding.logoFirstClub.setImageResource(selectedClubsList[randClubs.firstClub].logoId)
        binding.nameFirstClub.text = selectedClubsList[randClubs.firstClub].name
        binding.divisionFirstClub.text = selectedClubsList[randClubs.firstClub].division
        binding.countryFirstClub.text = selectedClubsList[randClubs.firstClub].country

        binding.logoSecondClub.setImageResource(selectedClubsList[randClubs.secondClub].logoId)
        binding.nameSecondClub.text = selectedClubsList[randClubs.secondClub].name
        binding.divisionSecondClub.text = selectedClubsList[randClubs.secondClub].division
        binding.countrySecondClub.text = selectedClubsList[randClubs.secondClub].country

        var strength1 = StrengthValue.HALF.value
        var strength2 = StrengthValue.HALF.value


        when (selectedClubsList[randClubs.firstClub].strength) {
            StrengthValue.HALF.value -> strength1 = StrengthValue.HALF.value
            StrengthValue.ONE.value -> strength1 = StrengthValue.ONE.value
            StrengthValue.ONE_AND_HALF.value -> strength1 = StrengthValue.ONE_AND_HALF.value
            StrengthValue.TWO.value -> strength1 = StrengthValue.TWO.value
            StrengthValue.TWO_AND_HALF.value -> strength1 = StrengthValue.TWO_AND_HALF.value
            StrengthValue.THREE.value -> strength1 = StrengthValue.THREE.value
            StrengthValue.THREE_AND_HALF.value -> strength1 =  StrengthValue.THREE_AND_HALF.value
            StrengthValue.FOUR.value -> strength1 =  StrengthValue.FOUR.value
            StrengthValue.FOUR_AND_HALF.value -> strength1 =   StrengthValue.FOUR_AND_HALF.value
            StrengthValue.FIVE.value -> strength1 = StrengthValue.FIVE.value
        }
        when (selectedClubsList[randClubs.secondClub].strength) {
            StrengthValue.HALF.value -> strength2 = StrengthValue.HALF.value
            StrengthValue.ONE.value -> strength2 = StrengthValue.ONE.value
            StrengthValue.ONE_AND_HALF.value -> strength2 = StrengthValue.ONE_AND_HALF.value
            StrengthValue.TWO.value -> strength2 = StrengthValue.TWO.value
            StrengthValue.TWO_AND_HALF.value -> strength2 = StrengthValue.TWO_AND_HALF.value
            StrengthValue.THREE.value -> strength2 = StrengthValue.THREE.value
            StrengthValue.THREE_AND_HALF.value -> strength2 =  StrengthValue.THREE_AND_HALF.value
            StrengthValue.FOUR.value -> strength2 =  StrengthValue.FOUR.value
            StrengthValue.FOUR_AND_HALF.value -> strength2 =   StrengthValue.FOUR_AND_HALF.value
            StrengthValue.FIVE.value -> strength2 = StrengthValue.FIVE.value
        }

        strengthStarSet(binding,strength1,listFirstClubStars[0],listFirstClubStars[1],listFirstClubStars[2],listFirstClubStars[3],listFirstClubStars[4])
        strengthStarSet(binding,strength2,listSecondClubStars[0],listSecondClubStars[1],listSecondClubStars[2],listSecondClubStars[3],listSecondClubStars[4])

        when(selectedClubsList[randClubs.firstClub].sex){
            getString(R.string.Man)-> binding.sexImage.setImageResource(R.drawable.male_icon)
            getString(R.string.Woman)->binding.sexImage.setImageResource(R.drawable.female_icon)
        }

    }
    private fun setStrengthMinMaxStar(){

        minimumStrengthForFilter = StrengthValue.HALF.value
        maximumStrengthForFilter = StrengthValue.FIVE.value

        initList(context,selectedClubsList,selectedCountryForFilter,selectedDivisionForFilter,minimumStrengthForFilter, maximumStrengthForFilter,selectedSexForFilter)

        minStarSet = selectedClubsList.minOf { it.strength}
        maxStarSet = selectedClubsList.maxOf { it.strength}

        strengthStarSet( binding,minStarSet,listMinimumStrengthStars[0],listMinimumStrengthStars[1],listMinimumStrengthStars[2],listMinimumStrengthStars[3],listMinimumStrengthStars[4])
        strengthStarSet( binding,maxStarSet,listMaximumStrengthStars[0],listMaximumStrengthStars[1],listMaximumStrengthStars[2],listMaximumStrengthStars[3],listMaximumStrengthStars[4])

        minimumStrengthForFilter = minStarSet
        maximumStrengthForFilter = maxStarSet

        selectedClubsList.clear()

    }
    private fun setMinimumStrengthClick(starBool:Boolean,strength: Double,starCount:Int){
        strengthStarSet(binding,strength,listMinimumStrengthStars[0],listMinimumStrengthStars[1],listMinimumStrengthStars[2],listMinimumStrengthStars[3],listMinimumStrengthStars[4])
        minimumStrengthForFilter = strength

        when(starCount){
            1-> minimumStrengthStar1 = starBool
            2-> minimumStrengthStar2 = starBool
            3-> minimumStrengthStar3 = starBool
            4-> minimumStrengthStar4 = starBool
            5-> minimumStrengthStar5 = starBool

        }

        if(minimumStrengthForFilter < minStarSet) {
            strengthStarSet( binding,minStarSet,listMinimumStrengthStars[0],listMinimumStrengthStars[1],listMinimumStrengthStars[2],listMinimumStrengthStars[3],listMinimumStrengthStars[4])
            minimumStrengthForFilter = minStarSet
        }

        else if(minimumStrengthForFilter > maxStarSet) {
            strengthStarSet( binding,maxStarSet,listMinimumStrengthStars[0],listMinimumStrengthStars[1],listMinimumStrengthStars[2],listMinimumStrengthStars[3],listMinimumStrengthStars[4])
            minimumStrengthForFilter = maxStarSet
        }

        if(minimumStrengthForFilter > maximumStrengthForFilter){
            maximumStrengthForFilter = minimumStrengthForFilter
            strengthStarSet( binding,maximumStrengthForFilter,listMaximumStrengthStars[0],listMaximumStrengthStars[1],listMaximumStrengthStars[2],listMaximumStrengthStars[3],listMaximumStrengthStars[4])
        }


    }
    private fun setMaximumStrengthClick(starBool:Boolean,strength: Double,starCount:Int){
        strengthStarSet(binding,strength,listMaximumStrengthStars[0],listMaximumStrengthStars[1],listMaximumStrengthStars[2],listMaximumStrengthStars[3],listMaximumStrengthStars[4])
        maximumStrengthForFilter = strength

        when(starCount){
            1-> maximumStrengthStar1 = starBool
            2-> maximumStrengthStar2 = starBool
            3-> maximumStrengthStar3 = starBool
            4-> maximumStrengthStar4 = starBool
            5-> maximumStrengthStar5 = starBool

        }

        if(maximumStrengthForFilter < minStarSet) {
            strengthStarSet( binding,minStarSet,listMaximumStrengthStars[0],listMaximumStrengthStars[1],listMaximumStrengthStars[2],listMaximumStrengthStars[3],listMaximumStrengthStars[4])
            maximumStrengthForFilter = minStarSet
        }

        else if(maximumStrengthForFilter > maxStarSet) {
            strengthStarSet( binding,maxStarSet,listMaximumStrengthStars[0],listMaximumStrengthStars[1],listMaximumStrengthStars[2],listMaximumStrengthStars[3],listMaximumStrengthStars[4])
            maximumStrengthForFilter = maxStarSet
        }

        if(minimumStrengthForFilter > maximumStrengthForFilter){
            minimumStrengthForFilter = maximumStrengthForFilter
            strengthStarSet( binding,maximumStrengthForFilter,listMaximumStrengthStars[0],listMaximumStrengthStars[1],listMaximumStrengthStars[2],listMaximumStrengthStars[3],listMaximumStrengthStars[4])
            strengthStarSet( binding,minimumStrengthForFilter,listMinimumStrengthStars[0],listMinimumStrengthStars[1],listMinimumStrengthStars[2],listMinimumStrengthStars[3],listMinimumStrengthStars[4])
        }

    }

    private fun checkClubs(context: Context,randFirstClub: Int, randSecondClub: Int) :RandClubs {
        var firstClubRand = randFirstClub
        var secondClubRand = randSecondClub

        while  (selectedClubsList.size>1 &&
            ((selectedClubsList[firstClubRand].sex != selectedClubsList[secondClubRand].sex) ||
                    (selectedClubsList[firstClubRand].country == context.getString(R.string.International) && selectedClubsList[secondClubRand].country != context.getString(R.string.International)) ||
                    selectedClubsList[secondClubRand].country == context.getString(R.string.International) && selectedClubsList[firstClubRand].country != context.getString(R.string.International))){
            firstClubRand = rand(selectedClubsList)
            secondClubRand = rand(selectedClubsList)
        }
        return RandClubs(firstClubRand,secondClubRand)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = requireContext()
    }
}