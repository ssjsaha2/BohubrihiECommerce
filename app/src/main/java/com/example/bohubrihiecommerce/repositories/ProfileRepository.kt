package com.example.bohubrihiecommerce.repositories

import androidx.lifecycle.MutableLiveData
import com.example.bohubrihiecommerce.models.Profile
import com.example.bohubrihiecommerce.retrofit.APIService
import com.example.bohubrihiecommerce.retrofit.RetrofitClient
import com.example.bohubrihiecommerce.utils.Resource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProfileRepository  {


    fun getProfile(token : String) : MutableLiveData<Resource<Profile>>{
        val profileLiveData  =  MutableLiveData<Resource<Profile>>()
        RetrofitClient.getAPIService().getProfile(token)
            .enqueue( object : Callback<Profile>{
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        profileLiveData.postValue(Resource.success(responseBody))
                    } else {
                        try {
                            val errorString = response.errorBody()?.string()
                            profileLiveData.postValue(Resource.error(errorString!!, null))
                        } catch (e: Exception) {
                            e.printStackTrace()
                            profileLiveData.postValue(Resource.error("Something went wrong", null))

                        }
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    profileLiveData.postValue(Resource.error("Something went wrong , Please check your internet",null))

                }

            })


        return profileLiveData

    }


    fun updateProfile(token : String , profile : Profile) : MutableLiveData<Resource<ResponseBody>>{
        val profileLiveData  =  MutableLiveData<Resource<ResponseBody>>()
        RetrofitClient.getAPIService().updateProfile(token,profile)
            .enqueue( object : Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        profileLiveData.postValue(Resource.success(responseBody))
                    } else {
                        try {
                            val errorString = response.errorBody()?.string()
                            profileLiveData.postValue(Resource.error(errorString!!, null))
                        } catch (e: Exception) {
                            e.printStackTrace()
                            profileLiveData.postValue(Resource.error("Something went wrong", null))

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    profileLiveData.postValue(Resource.error("Something went wrong , Please check your internet",null))

                }

            })


        return profileLiveData

    }
}