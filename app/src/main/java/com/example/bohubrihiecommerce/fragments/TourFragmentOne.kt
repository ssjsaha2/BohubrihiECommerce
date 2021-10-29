package com.example.bohubrihiecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bohubrihiecommerce.databinding.FragmentTourOneBinding
import com.example.bohubrihiecommerce.utils.Constants

class TourFragmentOne : Fragment() {

    private lateinit var binding : FragmentTourOneBinding
    private var description : String? = null
    private var drawableInt : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTourOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun getDataFromBundle(){
        arguments?.let {
            description = getString(it.getInt(Constants.tourDescription))
            drawableInt = it.getInt(Constants.tourImage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        initViews()
    }

    private fun initViews(){
        try {
            binding.description.text = description
            Glide.with(requireContext())
                .asBitmap()
                .load(drawableInt)
                .into(binding.image)
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
}