package com.example.bohubrihiecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.adapters.CategoryListItemAdapter
import com.example.bohubrihiecommerce.databinding.FragmentHomeBinding
import com.example.bohubrihiecommerce.models.Category
import com.example.bohubrihiecommerce.utils.Status
import com.example.bohubrihiecommerce.viewmodels.CategoryViewModel

class HomeFragment : Fragment() {
    private lateinit  var binding : FragmentHomeBinding
    private var categoryListAdapter : CategoryListItemAdapter? = null
    private val categoryViewModel : CategoryViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun initAdapter (categoryList : ArrayList<Category>){
        categoryListAdapter = CategoryListItemAdapter(requireContext(), categoryList)
        binding.categoryList.adapter = categoryListAdapter

        val layoutManager : LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.categoryList.layoutManager = layoutManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loader.visibility = View.VISIBLE
        categoryViewModel.getCategories()
        observeGetCategories()
    }

    private fun observeGetCategories(){
        categoryViewModel.getData()?.observe(viewLifecycleOwner , Observer {
            binding.loader.visibility = View.GONE

            if(it.status == Status.SUCCESS){
                val categoryList = it.data
                categoryList?.let {
                    initAdapter(it)
                }
            }else{
                Toast.makeText(requireContext() ,
                    getString(R.string.something_went_wrong),Toast.LENGTH_LONG).show()
            }
        })
    }
}