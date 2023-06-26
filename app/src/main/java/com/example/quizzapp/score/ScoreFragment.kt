package com.example.quizzapp.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {


    lateinit var binding3: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding3 = DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)

        val scoreArgs by navArgs<ScoreFragmentArgs>()
        binding3.displayResult.text = "${scoreArgs.name}! \n you have scored \n ${scoreArgs.score} /10"

        binding3.playAgain.setOnClickListener {view: View->
            view.findNavController().navigate(R.id.action_scoreFragment_to_titleFragment)

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //xu ly su kien quay lai ban dau

            view?.findNavController()?.navigate(R.id.action_scoreFragment_to_titleFragment)


        }

        return binding3.root
    }


}