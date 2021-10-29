package com.example.bohubrihiecommerce.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bohubrihiecommerce.activities.SignupOrLoginActivity
import com.example.bohubrihiecommerce.databinding.FragmentMoreBinding
import com.example.bohubrihiecommerce.utils.SharedPrefUtil
import com.example.bohubrihiecommerce.utils.TokenManager

class MoreFragment : Fragment() {

    private lateinit var binding : FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logoutView.setOnClickListener {
            val intent = Intent(requireContext() , SignupOrLoginActivity::class.java)
            startActivity(intent)
            SharedPrefUtil.saveToken(null)
            TokenManager.token = null
            requireActivity().finish()
        }
    }
}