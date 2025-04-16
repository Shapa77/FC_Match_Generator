package com.shapacreations.generatorfifa22

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.shapacreations.generatorfifa22.databinding.FragmentGenerateBinding


class GenerateFragment : Fragment() {

    private val binding by lazy { FragmentGenerateBinding.inflate(layoutInflater) }

    private lateinit var context: Context

    private var interAd: InterstitialAd? = null


    private lateinit var selectedCountryForFilter:String
    private lateinit var selectedDivisionForFilter:String
    private lateinit var selectedSexForFilter:String

    private lateinit var listFirstClubStars:List<ImageView>
    private lateinit var listSecondClubStars:List<ImageView>

    private lateinit var listMinimumStrengthStars:List<ImageView>
    private lateinit var listMaximumStrengthStars:List<ImageView>

    private val divisionListForSpinner:ArrayList<String> = arrayListOf()
    private val selectedClubsList = mutableListOf<ClubModel>()

    private var minStarSet = StrengthValue.HALF.value
    private var maxStarSet = StrengthValue.FIVE.value

    private var minimumStrengthForFilter = StrengthValue.HALF.value
    private var maximumStrengthForFilter = StrengthValue.FIVE.value

    private val minimumStrengthStars = mutableListOf(false, false, false, false, false)
    private val maximumStrengthStars = mutableListOf(false, false, false, false, false)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        initAds()
        return binding.root }

    override fun onResume() {
        super.onResume()
        binding.generateAdBanner.resume()
    }
    override fun onPause() {
        super.onPause()
        binding.generateAdBanner.pause()

    }
    override fun onDestroy() {
        super.onDestroy()
        binding.generateAdBanner.destroy()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = requireContext()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState)

        initValues()
        initSpinners()

        binding.apply {

            minimumStrengthStar1.setOnClickListener {
                if(!minimumStrengthStars[0]){ setMinimumStrengthClick(true,StrengthValue.ONE.value,1) }
                else{setMinimumStrengthClick(false,StrengthValue.HALF.value,1) }
            }
            minimumStrengthStar2.setOnClickListener {
                if(!minimumStrengthStars[1]){ setMinimumStrengthClick(true,StrengthValue.ONE_AND_HALF.value,2) }
                else{setMinimumStrengthClick(false,StrengthValue.TWO.value,2) }
            }
            minimumStrengthStar3.setOnClickListener {
                if(!minimumStrengthStars[2]){ setMinimumStrengthClick(true,StrengthValue.TWO_AND_HALF.value,3) }
                else{setMinimumStrengthClick(false,StrengthValue.THREE.value,3) }
            }
            minimumStrengthStar4.setOnClickListener {
                if(!minimumStrengthStars[3]){ setMinimumStrengthClick(true,StrengthValue.THREE_AND_HALF.value,4) }
                else{setMinimumStrengthClick(false,StrengthValue.FOUR.value,4) }
            }
            minimumStrengthStar5.setOnClickListener {
                if(!minimumStrengthStars[4]){ setMinimumStrengthClick(true,StrengthValue.FOUR_AND_HALF.value,5) }
                else{setMinimumStrengthClick(false,StrengthValue.FIVE.value,5) }
            }

            maximumStrengthStar1.setOnClickListener {
                if(!maximumStrengthStars[0]){ setMaximumStrengthClick(true,StrengthValue.ONE.value,1) }
                else{ setMaximumStrengthClick(false,StrengthValue.HALF.value,1) }
            }
            maximumStrengthStar2.setOnClickListener {
                if(!maximumStrengthStars[1]){ setMaximumStrengthClick(true,StrengthValue.ONE_AND_HALF.value,2) }
                else{ setMaximumStrengthClick(false,StrengthValue.TWO.value,2) }
            }
            maximumStrengthStar3.setOnClickListener {
                if(!maximumStrengthStars[2]){ setMaximumStrengthClick(true,StrengthValue.TWO_AND_HALF.value,3) }
                else{ setMaximumStrengthClick(false,StrengthValue.THREE.value,3) }
            }
            maximumStrengthStar4.setOnClickListener {
                if(!maximumStrengthStars[3]){ setMaximumStrengthClick(true,StrengthValue.THREE_AND_HALF.value,4) }
                else{ setMaximumStrengthClick(false,StrengthValue.FOUR.value,4) }
            }
            maximumStrengthStar5.setOnClickListener {
                if(!maximumStrengthStars[4]){ setMaximumStrengthClick(true,StrengthValue.FOUR_AND_HALF.value,5) }
                else{ setMaximumStrengthClick(false,StrengthValue.FIVE.value,5) }
            }

            generateButton.setOnClickListener {
                initList(context,selectedClubsList,selectedCountryForFilter,selectedDivisionForFilter,minimumStrengthForFilter, maximumStrengthForFilter,selectedSexForFilter)
                if (selectedClubsList.isNotEmpty()) {
                    setClubs(checkClubs(context,rand(selectedClubsList), rand(selectedClubsList)))
                    selectedClubsList.clear()
                }
                else showToast(context,R.string.Not_found)

            }

            advFilter.setOnClickListener { showAdInterstitial() }

            dataBase.setOnClickListener { changeFragment(DataBaseFragment(), gameId,true) }

        }
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

        binding.apply {
            listFirstClubStars = listOf(firstClubStar1, firstClubStar2, firstClubStar3, firstClubStar4, firstClubStar5)
            listSecondClubStars = listOf(secondClubStar1, secondClubStar2, secondClubStar3, secondClubStar4, secondClubStar5)
            listMinimumStrengthStars = listOf(minimumStrengthStar1, minimumStrengthStar2, minimumStrengthStar3, minimumStrengthStar4, minimumStrengthStar5)
            listMaximumStrengthStars = listOf(maximumStrengthStar1, maximumStrengthStar2, maximumStrengthStar3, maximumStrengthStar4, maximumStrengthStar5)
        }



    }
    private fun initSpinners(){

        selectedCountryForFilter = getStandardStringByValue(context,getString(R.string.All_countries))
        selectedDivisionForFilter = getStandardStringByValue(context,getString(R.string.All_divisions))

        divisionListForSpinner.apply {
            clear()
            add(getString(R.string.All_divisions))
        }

        val spinnerCountryAdapter = CountrySpinnerAdapter(context,countryListForSpinner)
        val spinnerDivisionAdapter = DivisionSpinnerAdapter(context, divisionListForSpinner)
        val spinnerGameAdapter = GameSpinnerAdapter(context, gameIconsForSpinner)

        binding.apply {

            spinnerCountry.adapter = spinnerCountryAdapter
            spinnerDivision.adapter = spinnerDivisionAdapter

            spinnerGame.apply {
                adapter = spinnerGameAdapter
                setSelection(gameId)
            }


            spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    selectedCountryForFilter = getStandardStringByValue(context,countryListForSpinner[position].text)
                    selectedDivisionForFilter = getStandardStringByValue(context,getString(R.string.All_divisions))

                    divisionListForSpinner.apply {
                        clear()
                        add(getString(R.string.All_divisions))
                    }

                    clubList.filter { it.country == selectedCountryForFilter } // фільтруємо за країною
                        .map { it.division } // отримуємо тільки дивізії
                        .distinct() // уникаємо дублювання
                        .forEach { division ->
                            if (!divisionListForSpinner.contains(division)) {
                                divisionListForSpinner.add(division)
                            }
                        }

                    if (divisionListForSpinner.size == 2) divisionListForSpinner.removeAt(0)
                    spinnerDivisionAdapter.notifyDataSetChanged()
                    binding.spinnerDivision.setSelection(0)
                    setStrengthMinMaxStar()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            spinnerDivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedDivisionForFilter = if(divisionListForSpinner[position] == getString(R.string.All_divisions)){
                        getStandardStringByValue(context,divisionListForSpinner[position])
                    } else divisionListForSpinner[position]

                    setStrengthMinMaxStar()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
    }

    private fun setClubs(randClubs: RandClubs){

        val strength1 = getStrengthValue(selectedClubsList[randClubs.firstClub].strength)
        val strength2 = getStrengthValue(selectedClubsList[randClubs.secondClub].strength)

        binding.apply {
            logoFirstClub.setImageResource(selectedClubsList[randClubs.firstClub].logoId)
            nameFirstClub.text = selectedClubsList[randClubs.firstClub].name
            divisionFirstClub.text = selectedClubsList[randClubs.firstClub].division
            countryFirstClub.text = getLocalizedStringByValue(context,selectedClubsList[randClubs.firstClub].country)

            logoSecondClub.setImageResource(selectedClubsList[randClubs.secondClub].logoId)
            nameSecondClub.text = selectedClubsList[randClubs.secondClub].name
            divisionSecondClub.text = selectedClubsList[randClubs.secondClub].division
            countrySecondClub.text = getLocalizedStringByValue(context,selectedClubsList[randClubs.secondClub].country)
        }

        strengthStarSet(listFirstClubStars,strength1)
        strengthStarSet(listSecondClubStars,strength2)

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

        strengthStarSet(listMinimumStrengthStars,minStarSet)
        strengthStarSet(listMaximumStrengthStars,maxStarSet)

        minimumStrengthForFilter = minStarSet
        maximumStrengthForFilter = maxStarSet

        selectedClubsList.clear()

    }
    private fun setMinimumStrengthClick(starBool:Boolean,strength: Double,starCount:Int){
        strengthStarSet(listMinimumStrengthStars,strength)
        minimumStrengthForFilter = strength

        if (starCount in 1..5) { minimumStrengthStars[starCount - 1] = starBool }

        when {
            minimumStrengthForFilter < minStarSet -> {
                strengthStarSet(listMinimumStrengthStars, minStarSet)
                minimumStrengthForFilter = minStarSet
            }
            minimumStrengthForFilter > maxStarSet -> {
                strengthStarSet(listMinimumStrengthStars, maxStarSet)
                minimumStrengthForFilter = maxStarSet
            }
        }


        if(minimumStrengthForFilter > maximumStrengthForFilter){
            maximumStrengthForFilter = minimumStrengthForFilter
            strengthStarSet(listMaximumStrengthStars,maximumStrengthForFilter)
        }


    }
    private fun setMaximumStrengthClick(starBool:Boolean,strength: Double,starCount:Int){
        strengthStarSet(listMaximumStrengthStars,strength)
        maximumStrengthForFilter = strength

        if (starCount in 1..5) { maximumStrengthStars[starCount - 1] = starBool }


        if(maximumStrengthForFilter < minStarSet) {
            strengthStarSet(listMaximumStrengthStars,minStarSet)
            maximumStrengthForFilter = minStarSet
        }

        else if(maximumStrengthForFilter > maxStarSet) {
            strengthStarSet(listMaximumStrengthStars,maxStarSet)
            maximumStrengthForFilter = maxStarSet
        }

        if(minimumStrengthForFilter > maximumStrengthForFilter){
            minimumStrengthForFilter = maximumStrengthForFilter
            strengthStarSet(listMaximumStrengthStars,maximumStrengthForFilter)
            strengthStarSet(listMinimumStrengthStars,minimumStrengthForFilter)
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

    private fun initAdInterstitial(){
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()
        InterstitialAd.load(context,INTER_AD_ID,adRequest,object : InterstitialAdLoadCallback(){

            override fun onAdFailedToLoad(ad: LoadAdError) {
                interAd = null
            }

            override fun onAdLoaded(ad: InterstitialAd) {
                interAd = ad
            }

        })
    }
    private fun showAdInterstitial(){
        if(interAd != null){

            interAd?.fullScreenContentCallback = object : FullScreenContentCallback(){

                override fun onAdDismissedFullScreenContent() = handleInterAdClosed()
                override fun onAdFailedToShowFullScreenContent(p0: AdError) = handleInterAdClosed()
                override fun onAdShowedFullScreenContent() = handleInterAdClosed()

            }
            interAd?.show(context as Activity)
        }
        else{ changeFragment(AdvancedGenerateFragment(), gameId,false) }
    }
    private fun handleInterAdClosed() {
        changeFragment(AdvancedGenerateFragment(), gameId, false)
        interAd = null
        initAdInterstitial()
    }

    private fun initAdBanner(){
        MobileAds.initialize(context)
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()
        binding.generateAdBanner.loadAd(adRequest)

    }

    private fun initAds(){
        initAdBanner()
        initAdInterstitial()
    }



}