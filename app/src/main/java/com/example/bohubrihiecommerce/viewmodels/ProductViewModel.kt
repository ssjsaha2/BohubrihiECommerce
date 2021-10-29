package com.example.bohubrihiecommerce.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bohubrihiecommerce.models.Category
import com.example.bohubrihiecommerce.models.Product
import com.example.bohubrihiecommerce.repositories.CategoryRepository
import com.example.bohubrihiecommerce.repositories.ProductRepository
import com.example.bohubrihiecommerce.utils.Resource
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private  var mutableData :  MutableLiveData<Resource<ArrayList<Product>>>?
    private var productDetailsData : MutableLiveData<Resource<Product>>?




    init {
        productDetailsData = MutableLiveData()
        mutableData = MutableLiveData()
    }

    fun getProducts(){
        viewModelScope.launch {
            mutableData =  ProductRepository.getProducts()
        }
    }

    fun getSingleProduct( id : String){
        viewModelScope.launch {
            productDetailsData =  ProductRepository.getSingleProduct(id)
        }
    }

    fun getData () : MutableLiveData<Resource<ArrayList<Product>>>?{
        return mutableData
    }

    fun getSingleProductData() : MutableLiveData<Resource<Product>>?{
        return productDetailsData
    }
}