package com.example.bohubrihiecommerce.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.databinding.ActivityHomeBinding
import com.example.bohubrihiecommerce.fragments.HomeFragment
import com.example.bohubrihiecommerce.fragments.MoreFragment
import com.example.bohubrihiecommerce.fragments.ProductFragment
import com.example.bohubrihiecommerce.fragments.ProfileFragment

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding

    private var homeFragment: HomeFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var moreFragment : MoreFragment? =null

    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNav()
        addMoreFragment()
        addProfileFragment()
        addHomeFragment()
        hideCurrentFragment()
        homeFragment?.let {
            showFragment(it)
        }
    }


    private fun addHomeFragment() {
        if (homeFragment == null) {
            homeFragment = HomeFragment()
        }
        homeFragment?.let { homeFragment ->
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, homeFragment)
                .commit()
            currentFragment = homeFragment
            hideCurrentFragment()
        }
    }



    private fun addProfileFragment() {
        if (profileFragment == null) {
            profileFragment = ProfileFragment()
        }
        profileFragment?.let { profileFragment ->
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, profileFragment)
                .commit()
            currentFragment = profileFragment
            hideCurrentFragment()
        }
    }

    private fun addMoreFragment() {
        if (moreFragment == null) {
            moreFragment = MoreFragment()
        }
        moreFragment?.let { moreFragment ->
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, moreFragment)
                .commitAllowingStateLoss()
            currentFragment = moreFragment
            hideCurrentFragment()
        }
    }

    private fun hideCurrentFragment() {
        try {
            currentFragment?.let {
                supportFragmentManager.beginTransaction().hide(it).commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showCurrentFragment() {
        try {
            currentFragment?.let {
                supportFragmentManager.beginTransaction().show(it).commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showFragment(fragment: Fragment) {
        try {
            hideCurrentFragment()
            supportFragmentManager.beginTransaction().show(fragment).commit()
            currentFragment = fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setNav() {
        binding.nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> profileFragment?.let { showFragment(it) }
                R.id.home -> homeFragment?.let { showFragment(it) }
                R.id.more -> moreFragment?.let { showFragment(it) }
            }
            true
        }
    }
}


