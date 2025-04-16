package com.shapacreations.generatorfifa22

import android.content.Context
import android.media.tv.AdRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.MobileAds
import com.shapacreations.generatorfifa22.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {

    private val binding by lazy { FragmentChoiceBinding.inflate(layoutInflater) }

    private lateinit var context: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { return binding.root }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = requireContext()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdBanner()

        val countryListForSpinnerFifa22Buff:List<ItemForSpinner> = listOf(
        ItemForSpinner(R.drawable.country_14,getString(R.string.All_countries),true),
        ItemForSpinner(R.drawable.country_1,getString(R.string.Argentina),false),
        ItemForSpinner(R.drawable.country_2,getString(R.string.Australia),false),
        ItemForSpinner(R.drawable.country_3,getString(R.string.Austria),false),
        ItemForSpinner(R.drawable.country_4,getString(R.string.Belgium),false),
        ItemForSpinner(R.drawable.country_5,getString(R.string.Brazil),false),
        ItemForSpinner(R.drawable.country_6,getString(R.string.China_PR),false),
        ItemForSpinner(R.drawable.country_8,getString(R.string.Denmark),false),
        ItemForSpinner(R.drawable.country_9,getString(R.string.England),false),
        ItemForSpinner(R.drawable.country_10,getString(R.string.France),false),
        ItemForSpinner(R.drawable.country_11,getString(R.string.Germany),false),
        ItemForSpinner(R.drawable.country_12,getString(R.string.Netherlands),false),
        ItemForSpinner(R.drawable.country_13,getString(R.string.India),false),
        ItemForSpinner(R.drawable.country_14,getString(R.string.International),true),
        ItemForSpinner(R.drawable.country_15,getString(R.string.Italy),false),
        ItemForSpinner(R.drawable.country_16,getString(R.string.Japan),false),
        ItemForSpinner(R.drawable.country_26,getString(R.string.Korea_Republic),false),
        ItemForSpinner(R.drawable.country_17,getString(R.string.Mexico),false),
        ItemForSpinner(R.drawable.country_18,getString(R.string.Norway),false),
        ItemForSpinner(R.drawable.country_19,getString(R.string.Poland),false),
        ItemForSpinner(R.drawable.country_20,getString(R.string.Portugal),false),
        ItemForSpinner(R.drawable.country_21,getString(R.string.Republic_of_Ireland),false),
        ItemForSpinner(R.drawable.country_22,getString(R.string.Rest_of_World),false),
        ItemForSpinner(R.drawable.country_23,getString(R.string.Romania),false),
        ItemForSpinner(R.drawable.country_24,getString(R.string.Saudi_Arabia),false),
        ItemForSpinner(R.drawable.country_25,getString(R.string.Scotland),false),
        ItemForSpinner(R.drawable.country_27,getString(R.string.Spain),false),
        ItemForSpinner(R.drawable.country_28,getString(R.string.Sweden),false),
        ItemForSpinner(R.drawable.country_29,getString(R.string.Switzerland),false),
        ItemForSpinner(R.drawable.country_30,getString(R.string.Turkey),false),
        ItemForSpinner(R.drawable.country_31,getString(R.string.United_States),false)
        )
        val countryListForSpinnerFifa23Buff:List<ItemForSpinner> = listOf(
            ItemForSpinner(R.drawable.country_14,getString(R.string.All_countries),true),
            ItemForSpinner(R.drawable.country_1,getString(R.string.Argentina),false),
            ItemForSpinner(R.drawable.country_2,getString(R.string.Australia),false),
            ItemForSpinner(R.drawable.country_3,getString(R.string.Austria),false),
            ItemForSpinner(R.drawable.country_4,getString(R.string.Belgium),false),
            ItemForSpinner(R.drawable.country_6,getString(R.string.China_PR),false),
            ItemForSpinner(R.drawable.country_8,getString(R.string.Denmark),false),
            ItemForSpinner(R.drawable.country_9,getString(R.string.England),true),
            ItemForSpinner(R.drawable.country_10,getString(R.string.France),true),
            ItemForSpinner(R.drawable.country_11,getString(R.string.Germany),false),
            ItemForSpinner(R.drawable.country_12,getString(R.string.Netherlands),false),
            ItemForSpinner(R.drawable.country_13,getString(R.string.India),false),
            ItemForSpinner(R.drawable.country_14,getString(R.string.International),true),
            ItemForSpinner(R.drawable.country_15,getString(R.string.Italy),false),
            ItemForSpinner(R.drawable.country_26,getString(R.string.Korea_Republic),false),
            ItemForSpinner(R.drawable.country_18,getString(R.string.Norway),false),
            ItemForSpinner(R.drawable.country_19,getString(R.string.Poland),false),
            ItemForSpinner(R.drawable.country_20,getString(R.string.Portugal),false),
            ItemForSpinner(R.drawable.country_21,getString(R.string.Republic_of_Ireland),false),
            ItemForSpinner(R.drawable.country_22,getString(R.string.Rest_of_World),true),
            ItemForSpinner(R.drawable.country_23,getString(R.string.Romania),false),
            ItemForSpinner(R.drawable.country_24,getString(R.string.Saudi_Arabia),false),
            ItemForSpinner(R.drawable.country_25,getString(R.string.Scotland),false),
            ItemForSpinner(R.drawable.country_27,getString(R.string.Spain),false),
            ItemForSpinner(R.drawable.country_28,getString(R.string.Sweden),false),
            ItemForSpinner(R.drawable.country_29,getString(R.string.Switzerland),false),
            ItemForSpinner(R.drawable.country_30,getString(R.string.Turkey),false),
            ItemForSpinner(R.drawable.country_31,getString(R.string.United_States),true)




        )
        val countryListForSpinnerFifa24Buff:List<ItemForSpinner> = listOf(
            ItemForSpinner(R.drawable.country_14,getString(R.string.All_countries),true),
            ItemForSpinner(R.drawable.country_1,getString(R.string.Argentina),false),
            ItemForSpinner(R.drawable.country_2,getString(R.string.Australia),false),
            ItemForSpinner(R.drawable.country_3,getString(R.string.Austria),false),
            ItemForSpinner(R.drawable.country_4,getString(R.string.Belgium),false),
            ItemForSpinner(R.drawable.country_6,getString(R.string.China_PR),false),
            ItemForSpinner(R.drawable.country_8,getString(R.string.Denmark),false),
            ItemForSpinner(R.drawable.country_9,getString(R.string.England),true),
            ItemForSpinner(R.drawable.country_10,getString(R.string.France),true),
            ItemForSpinner(R.drawable.country_11,getString(R.string.Germany),true),
            ItemForSpinner(R.drawable.country_12,getString(R.string.Netherlands),false),
            ItemForSpinner(R.drawable.country_13,getString(R.string.India),false),
            ItemForSpinner(R.drawable.country_14,getString(R.string.International),true),
            ItemForSpinner(R.drawable.country_15,getString(R.string.Italy),false),
            ItemForSpinner(R.drawable.country_26,getString(R.string.Korea_Republic),false),
            ItemForSpinner(R.drawable.country_18,getString(R.string.Norway),false),
            ItemForSpinner(R.drawable.country_19,getString(R.string.Poland),false),
            ItemForSpinner(R.drawable.country_20,getString(R.string.Portugal),false),
            ItemForSpinner(R.drawable.country_21,getString(R.string.Republic_of_Ireland),false),
            ItemForSpinner(R.drawable.country_22,getString(R.string.Rest_of_World),true),
            ItemForSpinner(R.drawable.country_23,getString(R.string.Romania),false),
            ItemForSpinner(R.drawable.country_24,getString(R.string.Saudi_Arabia),false),
            ItemForSpinner(R.drawable.country_25,getString(R.string.Scotland),false),
            ItemForSpinner(R.drawable.country_27,getString(R.string.Spain),true),
            ItemForSpinner(R.drawable.country_28,getString(R.string.Sweden),false),
            ItemForSpinner(R.drawable.country_29,getString(R.string.Switzerland),false),
            ItemForSpinner(R.drawable.country_30,getString(R.string.Turkey),false),
            ItemForSpinner(R.drawable.country_31,getString(R.string.United_States),true)

        )
        val countryListForSpinnerFifa25Buff:List<ItemForSpinner> = listOf(
            ItemForSpinner(R.drawable.country_14,getString(R.string.All_countries),true),
            ItemForSpinner(R.drawable.country_1,getString(R.string.Argentina),false),
            ItemForSpinner(R.drawable.country_2,getString(R.string.Australia),false),
            ItemForSpinner(R.drawable.country_3,getString(R.string.Austria),false),
            ItemForSpinner(R.drawable.country_4,getString(R.string.Belgium),false),
            ItemForSpinner(R.drawable.country_6,getString(R.string.China_PR),false),
            ItemForSpinner(R.drawable.country_8,getString(R.string.Denmark),false),
            ItemForSpinner(R.drawable.country_9,getString(R.string.England),true),
            ItemForSpinner(R.drawable.country_10,getString(R.string.France),true),
            ItemForSpinner(R.drawable.country_11,getString(R.string.Germany),true),
            ItemForSpinner(R.drawable.country_12,getString(R.string.Netherlands),false),
            ItemForSpinner(R.drawable.country_13,getString(R.string.India),false),
            ItemForSpinner(R.drawable.country_14,getString(R.string.International),true),
            ItemForSpinner(R.drawable.country_15,getString(R.string.Italy),false),
            ItemForSpinner(R.drawable.country_26,getString(R.string.Korea_Republic),false),
            ItemForSpinner(R.drawable.country_18,getString(R.string.Norway),false),
            ItemForSpinner(R.drawable.country_19,getString(R.string.Poland),false),
            ItemForSpinner(R.drawable.country_20,getString(R.string.Portugal),false),
            ItemForSpinner(R.drawable.country_21,getString(R.string.Republic_of_Ireland),false),
            ItemForSpinner(R.drawable.country_22,getString(R.string.Rest_of_World),true),
            ItemForSpinner(R.drawable.country_23,getString(R.string.Romania),false),
            ItemForSpinner(R.drawable.country_24,getString(R.string.Saudi_Arabia),false),
            ItemForSpinner(R.drawable.country_25,getString(R.string.Scotland),false),
            ItemForSpinner(R.drawable.country_27,getString(R.string.Spain),true),
            ItemForSpinner(R.drawable.country_28,getString(R.string.Sweden),false),
            ItemForSpinner(R.drawable.country_29,getString(R.string.Switzerland),false),
            ItemForSpinner(R.drawable.country_30,getString(R.string.Turkey),false),
            ItemForSpinner(R.drawable.country_31,getString(R.string.United_States),true)

        )


        countryListForSpinnerFifa22 = countryListForSpinnerFifa22Buff
        countryListForSpinnerFifa23 = countryListForSpinnerFifa23Buff
        countryListForSpinnerFifa24 = countryListForSpinnerFifa24Buff
        countryListForSpinnerFifa25 = countryListForSpinnerFifa25Buff

        setupChoiceButton(binding.choiceFifa22, GameId.FIFA22)
        setupChoiceButton(binding.choiceFifa23, GameId.FIFA23)
        setupChoiceButton(binding.choiceFc24, GameId.FC24)
        setupChoiceButton(binding.choiceFc25, GameId.FC25)

    }

    private fun setupChoiceButton(button: View, gameId: GameId) {
        button.setOnClickListener {
            changeFragment(GenerateFragment(), gameId.ordinal, true)
        }
    }


    private fun initAdBanner(){
        MobileAds.initialize(context)
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()
        binding.choiceAdBanner.loadAd(adRequest)
    }

    override fun onResume() {
        super.onResume()
        binding.choiceAdBanner.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.choiceAdBanner.pause()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.choiceAdBanner.destroy()

    }






}

