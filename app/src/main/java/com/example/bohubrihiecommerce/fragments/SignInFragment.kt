package com.example.bohubrihiecommerce.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bohubrihiecommerce.activities.HomeActivity
import com.example.bohubrihiecommerce.databinding.FragmentSigninBinding
import com.example.bohubrihiecommerce.databinding.FragmentSignupBinding
import com.example.bohubrihiecommerce.models.User
import com.example.bohubrihiecommerce.utils.SharedPrefUtil
import com.example.bohubrihiecommerce.utils.Status
import com.example.bohubrihiecommerce.utils.TokenManager
import com.example.bohubrihiecommerce.viewmodels.SignupAndLoginViewModel

class SignInFragment : Fragment() {

    private var isEmailGiven = false
    private var isPasswordGiven = false
    private var buttonStateLiveData = MutableLiveData<Boolean>()
    private val signupAndLoginViewModel: SignupAndLoginViewModel by viewModels()

    private var email: String? = null
    private var password: String? = null


    private lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeLayout() {
        buttonStateLiveData.observe(viewLifecycleOwner, {
            if (isEmailGiven  && isPasswordGiven) {
                binding.continueButton.isEnabled = true
            } else {
                binding.continueButton.isEnabled = false

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLayout()

        binding.emailEditText.addTextChangedListener {
            if (it == null || it.toString() == "") {
                isEmailGiven = false
                email = null
            } else {
                isEmailGiven = true
                email = it.toString()
            }
            buttonStateLiveData.postValue(isEmailGiven)
        }

        binding.passwordEditText.addTextChangedListener {
            if (it == null || it.toString() == "") {
                isPasswordGiven = false
                password = null
            } else {
                isPasswordGiven = true
                password = it.toString()
            }
            buttonStateLiveData.postValue(isPasswordGiven)
        }
        binding.continueButton.setOnClickListener {
            try {
                val user = User(null, email!!, password!!)
                signupAndLoginViewModel.doSignIn(user)
                showLoader()
                observeSignInResponse()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showLoader() {
        binding.loaderLayout.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.loaderLayout.visibility = View.GONE
    }

    private fun switchToHomeActivity(){
        val intent = Intent(requireActivity() , HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
    }
    private fun observeSignInResponse() {
        signupAndLoginViewModel.getSignInData()?.observe(viewLifecycleOwner, Observer {
            hideLoader()
            if (it.status == Status.SUCCESS) {
                val signupResponse = it.data
                TokenManager.token ="Bearer " +signupResponse?.token
                Toast.makeText(requireContext(), signupResponse?.message, Toast.LENGTH_LONG).show()
                SharedPrefUtil.saveToken("Bearer "+signupResponse?.token)

                switchToHomeActivity()
            } else {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}