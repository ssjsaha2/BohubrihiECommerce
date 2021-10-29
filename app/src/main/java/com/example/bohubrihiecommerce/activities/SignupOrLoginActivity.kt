package com.example.bohubrihiecommerce.activities

import android.os.Bundle
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.fragments.SignInFragment
import com.example.bohubrihiecommerce.fragments.SignupFragment
import com.example.bohubrihiecommerce.fragments.SignupOrSigninFragment

class SignupOrLoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_or_login)
        switchToSignupOrLoginFragment()
    }

    private fun switchToSignupOrLoginFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,SignupOrSigninFragment())
            .commit()
    }

    fun switchToSignupFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,SignupFragment())
            .addToBackStack("SignupFragment")
            .commit()
    }

    fun switchToSignInFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,SignInFragment())
            .addToBackStack("SignupFragment")
            .commit()
    }

}