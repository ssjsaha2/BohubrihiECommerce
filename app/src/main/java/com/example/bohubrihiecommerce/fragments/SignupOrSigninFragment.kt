package com.example.bohubrihiecommerce.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.activities.SignupOrLoginActivity
import com.example.bohubrihiecommerce.databinding.FragmentSignupOrLoginBinding

class SignupOrSigninFragment :Fragment(R.layout.fragment_signup_or_login){

    private lateinit var binding : FragmentSignupOrLoginBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupOrLoginBinding.bind(view)
        binding.signinButton.setOnClickListener {
            (activity as SignupOrLoginActivity).switchToSignInFragment()

        }
        binding.signupButton.setOnClickListener {
            (activity as SignupOrLoginActivity).switchToSignupFragment()

        }
    }
}