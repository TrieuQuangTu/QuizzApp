package com.example.quizzapp.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)

        binding.startBtn.setOnClickListener {view:View ->



            //kiem tra xem editText co rong ko?
            if (binding.etName.text!!.isNotEmpty()){

                //sang fragment game thong qua ACTION trong navigation component
                val action = TitleFragmentDirections.actionTitleFragmentToGameFragment()
                action.name = binding.etName.text.toString()

                view.findNavController().navigate(action)
            }else
            {
                Toast.makeText(context,"Enter your name",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root

    }


}