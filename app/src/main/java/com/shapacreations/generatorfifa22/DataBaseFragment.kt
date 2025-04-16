package com.shapacreations.generatorfifa22

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import com.shapacreations.generatorfifa22.databinding.FragmentDataBaseBinding


class DataBaseFragment : Fragment() {

    private val binding by lazy { FragmentDataBaseBinding.inflate(layoutInflater) }

    private lateinit var context: Context

    private lateinit var selectedCountryForFilter:String
    private lateinit var selectedDivisionForFilter:String
    private lateinit var selectedSexForFilter:String

    private var selectedClubsList = mutableListOf<ClubModel>()


    private val divisionListForSpinner:ArrayList<String> = arrayListOf()

    private lateinit var dbRecyclerViewAdapter : DbRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { return binding.root }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = requireContext()
    }

    override fun onResume() {
        super.onResume()
        binding.dataBaseAdBanner.resume()
    }
    override fun onPause() {
        super.onPause()
        binding.dataBaseAdBanner.pause()

    }
    override fun onDestroy() {
        super.onDestroy()
        binding.dataBaseAdBanner.destroy()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState)

        initValues()
        initSpinners()
        initDbRecyclerView()
        initAdBanner()

    }

    private fun initAdBanner(){
        MobileAds.initialize(context)
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()
        binding.dataBaseAdBanner.loadAd(adRequest)

    }

    private fun initDbRecyclerView(){
        dbRecyclerViewAdapter = DbRecyclerViewAdapter(context)
        binding.apply {
            dbRecyclerView.layoutManager = LinearLayoutManager(context)
            dbRecyclerView.adapter = dbRecyclerViewAdapter

            initList(context,selectedClubsList,selectedCountryForFilter,selectedDivisionForFilter,StrengthValue.HALF.value, StrengthValue.FIVE.value,selectedSexForFilter)

            dbRecyclerViewAdapter.refreshData(selectedClubsList)


        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshDbRecyclerView(){
        selectedClubsList.clear()
        initList(context,selectedClubsList,selectedCountryForFilter,selectedDivisionForFilter,StrengthValue.HALF.value, StrengthValue.FIVE.value,selectedSexForFilter)
        dbRecyclerViewAdapter.refreshData(selectedClubsList)

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

            spinnerDbFilterCountry.adapter = spinnerCountryAdapter
            spinnerDbFilterDivision.adapter = spinnerDivisionAdapter

            dbSpinnerGame.apply {
                adapter = spinnerGameAdapter
                setSelection(gameId)
            }


            spinnerDbFilterCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
                    binding.spinnerDbFilterDivision.setSelection(0)
                    refreshDbRecyclerView()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            spinnerDbFilterDivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedDivisionForFilter = if(divisionListForSpinner[position] == getString(R.string.All_divisions)){
                        getStandardStringByValue(context,divisionListForSpinner[position])
                    } else divisionListForSpinner[position]
                    refreshDbRecyclerView()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            dbSpinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
}