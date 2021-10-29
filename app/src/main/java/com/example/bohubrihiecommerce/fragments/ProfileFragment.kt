package com.example.bohubrihiecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.bohubrihiecommerce.databinding.FragmentProfileBinding
import com.example.bohubrihiecommerce.models.Profile
import com.example.bohubrihiecommerce.utils.Status
import com.example.bohubrihiecommerce.utils.TokenManager
import com.example.bohubrihiecommerce.viewmodels.ProfileViewModel
import java.lang.Exception

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val profileViewModel: ProfileViewModel by viewModels()

    private var addressOne : String? = null
    private var addressTwo : String? = null
    private var city : String? = null
    private var postCode : String? = null
    private var phone : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setEditTextListeners(){
        binding.postCodeEditText.addTextChangedListener {
            if(it != null){
                postCode = it.toString()
            }
        }
        binding.phoneEditText.addTextChangedListener {
            if(it != null){
                phone = it.toString()
            }
        }
        binding.cityEditText.addTextChangedListener {
            if(it != null){
                city = it.toString()
            }
        }
        binding.addressOneEditText.addTextChangedListener {
            if(it != null){
                addressOne = it.toString()
            }
        }
        binding.addressTwoEditText.addTextChangedListener{
            if(it != null){
                addressTwo = it.toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEditTextListeners()
        binding.loaderConatiner.visibility= View.VISIBLE
        getProfile()
        binding.updateButton.setOnClickListener {
            updateProfile()
        }
    }

    private fun updateProfile() {
        binding.loaderConatiner.visibility = View.VISIBLE

        if (postCode == null) {
            TokenManager.token?.let {
                val profile = Profile(phone, addressOne, addressTwo, city, null)
                profileViewModel.updateProfile(it , profile)
            }

        }else{
            TokenManager.token?.let {
                val profile = Profile(phone, addressOne, addressTwo, city, null)
                profileViewModel.updateProfile(it , profile)
            }
        }
        observeProfileUpdate()
    }

    private fun getProfile() {
        TokenManager.token?.let {
            profileViewModel.getProfile(it)
            observeProfileFetch()
        }
    }


    private fun updateUi(profile: Profile) {
        try {
            binding.loaderConatiner.visibility= View.GONE

            binding.addressOneEditText.setText(profile.address1)
            binding.addressTwoEditText.setText(profile.address2)
            binding.cityEditText.setText(profile.city)
            binding.phoneEditText.setText(profile.phone)
            binding.postCodeEditText.setText(profile.postcode.toString())
        }catch ( e : Exception){
            e.toString()
        }
    }

    private fun observeProfileFetch() {
        profileViewModel.getProfileGetLiveData().observe(
            viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    it.data?.let {
                        updateUi(it)
                    }
                } else {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun observeProfileUpdate() {
        profileViewModel.getProfileUpdateLiveData().observe(
            viewLifecycleOwner, Observer {
                binding.loaderConatiner.visibility = View.GONE

                if (it.status == Status.SUCCESS) {
                    Toast.makeText(requireContext(), "Update Successful", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}
