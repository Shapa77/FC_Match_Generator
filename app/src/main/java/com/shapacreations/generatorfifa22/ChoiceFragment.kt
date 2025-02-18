package com.shapacreations.generatorfifa22

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shapacreations.generatorfifa22.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {

    private val binding by lazy { FragmentChoiceBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.choiceFifa22.setOnClickListener { changeFragment(GenerateFragment(),GameId.FIFA22.ordinal,false)}
        binding.choiceFifa23.setOnClickListener { changeFragment(GenerateFragment(),GameId.FIFA23.ordinal,false)}
        binding.choiceFc24.setOnClickListener { changeFragment(GenerateFragment(),GameId.FC24.ordinal,false)}
        binding.choiceFc25.setOnClickListener { changeFragment(GenerateFragment(),GameId.FC25.ordinal,false)}

    }

}