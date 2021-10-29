package com.example.bohubrihiecommerce.activities

import android.os.Bundle
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.fragments.ProductDetailsFragment
import com.example.bohubrihiecommerce.fragments.ProductFragment
import com.example.bohubrihiecommerce.fragments.SuccessFragment
import com.example.bohubrihiecommerce.utils.Constants

class ProductActivity : BaseActivity() {

    private var categoryName : String?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        getDataFromIntent()
        switchToProductFragment()
    }

    private fun getDataFromIntent(){
       categoryName =  intent.getStringExtra(Constants.CATEGORY)
    }

    fun switchToProductFragment(){
        val productFragment = ProductFragment()
        val bundle = Bundle().apply {
            this.putString(Constants.CATEGORY , categoryName)
        }
        productFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add( R.id.fragmentContainer,productFragment)
            .commit()
    }
    fun switchToProductDetailsFragment(bundle : Bundle){
        val productDetailsFragment = ProductDetailsFragment()
        productDetailsFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add( R.id.fragmentContainer,productDetailsFragment)
            .commit()
    }

    fun switchToSuccessFragment(){
        val successFragment = SuccessFragment()
        supportFragmentManager.beginTransaction().add( R.id.fragmentContainer,successFragment)
            .commit()
    }
}