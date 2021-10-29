package com.example.bohubrihiecommerce.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.bohubrihiecommerce.adapters.TourPagerAdapter
import com.example.bohubrihiecommerce.databinding.ActivityTourBinding
import com.example.bohubrihiecommerce.utils.SharedPrefUtil
import com.example.bohubrihiecommerce.utils.TokenManager

class TourActivity : BaseActivity() {
    private lateinit var binding : ActivityTourBinding
    private var tourPagerAdapter :  TourPagerAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourBinding.inflate(layoutInflater)
        checkIfSwitchToHomeNeeded()
        val view = binding.root
        setContentView(view)
        initViewPagerAdapter()
        setButtonAction()
    }

    private fun checkIfSwitchToHomeNeeded(){
        if(SharedPrefUtil.getToken() == null){
            return
        }else{
            val intent = Intent(this,HomeActivity::class.java)
            TokenManager.token = SharedPrefUtil.getToken()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun initViewPagerAdapter(){
        tourPagerAdapter = TourPagerAdapter(supportFragmentManager ,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.pager.adapter = tourPagerAdapter
        binding.tabDots.setupWithViewPager(binding.pager)
    }

    private fun setButtonAction(){
        binding.proceedButton.setOnClickListener {
            val intent = Intent(this,SignupOrLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}