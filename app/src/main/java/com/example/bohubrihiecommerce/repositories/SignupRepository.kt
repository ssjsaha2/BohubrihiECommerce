package com.example.bohubrihiecommerce.repositories

import androidx.lifecycle.MutableLiveData
import com.example.bohubrihiecommerce.models.SignupResponse
import com.example.bohubrihiecommerce.models.User
import com.example.bohubrihiecommerce.retrofit.RetrofitClient
import com.example.bohubrihiecommerce.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupRepository {


    fun signupUser(user: User): MutableLiveData<Resource<SignupResponse>> {
        val mutableData = MutableLiveData<Resource<SignupResponse>>()
        RetrofitClient.getAPIService().doSignUp(user)
            .enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
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

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    mutableData.postValue(Resource.error("Something went wrong , Please check your internet",null))
                }

            })
        return mutableData
    }

    fun signInUser(user: User): MutableLiveData<Resource<SignupResponse>> {
        val mutableData = MutableLiveData<Resource<SignupResponse>>()
        RetrofitClient.getAPIService().doSignin(user)
            .enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
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

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    mutableData.postValue(Resource.error("Something went wrong , Please check your internet",null))
                }

            })
        return mutableData
    }
}