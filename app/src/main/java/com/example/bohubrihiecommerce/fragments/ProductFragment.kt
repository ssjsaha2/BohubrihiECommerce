package com.example.bohubrihiecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bohubrihiecommerce.adapters.ProductAdapter
import com.example.bohubrihiecommerce.databinding.FragmentProductBinding
import com.example.bohubrihiecommerce.models.Product
import com.example.bohubrihiecommerce.utils.Constants
import com.example.bohubrihiecommerce.utils.Status
import com.example.bohubrihiecommerce.viewmodels.ProductViewModel

class ProductFragment :Fragment() {

    private lateinit  var binding : FragmentProductBinding
    private var productAdapter : ProductAdapter? = null
    private var categoryName : String? = null
    private val productViewModel : ProductViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun getDataFromBundle(){
        arguments?.let {
            categoryName = it.getString(Constants.CATEGORY)
        }
    }

    private fun getProducts(){

        productViewModel.getProducts()
        observeResponse()
    }

    private fun initAdapter(productList : ArrayList<Product>){
        productAdapter = ProductAdapter(requireContext() , productList)
        binding.productList.adapter = productAdapter
        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        binding.productList.layoutManager = gridLayoutManager
    }

    private fun observeResponse(){
        productViewModel.getData()?.observe(viewLifecycleOwner, Observer {
            binding.loader.visibility = View.GONE
            if(it.status == Status.SUCCESS){
                var productList = it.data?.filter {
                    it.category?.name == categoryName
                }
                initAdapter(ArrayList(productList))
            }else{
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        getProducts()

    }

}