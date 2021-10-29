package com.example.bohubrihiecommerce.repositories

import androidx.lifecycle.MutableLiveData
import com.example.bohubrihiecommerce.models.Category
import com.example.bohubrihiecommerce.models.SignupResponse
import com.example.bohubrihiecommerce.models.User
import com.example.bohubrihiecommerce.retrofit.RetrofitClient
import com.example.bohubrihiecommerce.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository {

    fun getCategories(): MutableLiveData<Resource<ArrayList<Category>>> {
        val mutableData = MutableLiveData<Resource<ArrayList<Category>>>()
        RetrofitClient.getAPIService().getCategories()
            .enqueue(object : Callback<ArrayList<Category>> {
                override fun onResponse(
                    call: Call<ArrayList<Category>>,
                    response: Response<ArrayList<Category>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        mutableData.postValue(Resource.success(responseBody))
                    } else {
                        try {
                            val errorString = response.errorBody()?.string()
                            mutableData.postValue(Resource.error(errorString!!,null))
                        }catch (e : Exception){
                            e.printStackTrace()
                            mutableData.postValue(Resource.error("Something went wrong",null))

                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Category>>, t: Throwable) {
                    mutableData.postValue(Resource.error("Something went wrong , Please check your internet",null))
                }

            })
        return mutableData
    }
}