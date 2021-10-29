package com.example.bohubrihiecommerce.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.fragments.TourFragmentOne
import com.example.bohubrihiecommerce.utils.Constants

class TourPagerAdapter(fragmentManager : FragmentManager , behaviour : Int) :
    FragmentStatePagerAdapter(fragmentManager ,behaviour) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        var bundle = Bundle()
        val tourFragmentOne = TourFragmentOne()

        if(position == 0) {
            bundle = Bundle().apply {
                putInt(Constants.tourImage, R.drawable.e_commerce_logo)
                putInt(Constants.tourDescription , R.string.welcome_tour_one)
            }
            tourFragmentOne.arguments = bundle
        }else{
            bundle = Bundle().apply {
                putInt(Constants.tourImage, R.drawable.ic_mobile_login)
                putInt(Constants.tourDescription , R.string.welcome_tour_two)
            }
            tourFragmentOne.arguments = bundle
        }
      return tourFragmentOne
    }
}