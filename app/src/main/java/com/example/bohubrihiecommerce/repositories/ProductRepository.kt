package com.example.bohubrihiecommerce.repositories

import androidx.lifecycle.MutableLiveData
import com.example.bohubrihiecommerce.models.Category
import com.example.bohubrihiecommerce.models.Product
import com.example.bohubrihiecommerce.retrofit.RetrofitClient
import com.example.bohubrihiecommerce.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProductRepository {

    fun getProducts(): MutableLiveData<Resource<ArrayList<Product>>> {
        val mutableData = MutableLiveData<Resource<ArrayList<Product>>>()
        RetrofitClient.getAPIService().getProducts(
            "asc","name",50
        )
            .enqueue(object : Callback<ArrayList<Product>> {
                override fun onResponse(
                    call: Call<ArrayList<Product>>,
                    response: Response<ArrayList<Product>>
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

                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    mutableData.postValue(Resource.error("Something went wrong , Please check your internet",null))
                }

            })
        return mutableData
    }


    fun getSingleProduct( id : String): MutableLiveData<Resource<Product>> {
        val mutableData = MutableLiveData<Resource<Product>>()
        RetrofitClient.getAPIService().getSingleProduct(id).enqueue(object : Callback<Product> {
                override fun onResponse(
                    call: Call<Product>,
                    response: Response<Product>
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

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    mutableData.postValue(Resource.error("Something went wrong , Please check your internet",null))
                }

            })
        return mutableData
    }
}