package com.example.bohubrihiecommerce.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bohubrihiecommerce.models.Category
import com.example.bohubrihiecommerce.models.SignupResponse
import com.example.bohubrihiecommerce.models.User
import com.example.bohubrihiecommerce.repositories.CategoryRepository
import com.example.bohubrihiecommerce.repositories.SignupRepository
import com.example.bohubrihiecommerce.utils.Resource
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private  var mutableData :  MutableLiveData<Resource<ArrayList<Category>>>?

    private var categoryRepository : CategoryRepository? = null

    init {
        categoryRepository = CategoryRepository()
        mutableData = MutableLiveData()
    }

    fun getCategories(){
        viewModelScope.launch {
            mutableData =  categoryRepository?.getCategories()
        }
    }

    fun getData () : MutableLiveData<Resource<ArrayList<Category>>>?{
        return mutableData
    }
}