package com.example.bohubrihiecommerce.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bohubrihiecommerce.models.SignupResponse
import com.example.bohubrihiecommerce.models.User
import com.example.bohubrihiecommerce.repositories.SignupRepository
import com.example.bohubrihiecommerce.utils.Resource
import kotlinx.coroutines.launch

class SignupAndLoginViewModel : ViewModel() {

    private  var mutableData :  MutableLiveData<Resource<SignupResponse>>?

    private var signinData : MutableLiveData<Resource<SignupResponse>>?

    private var signupRepository : SignupRepository? = null

    init {
        signupRepository = SignupRepository()
        mutableData = MutableLiveData()
        signinData = MutableLiveData()
    }

    fun doSignup(user : User){
        viewModelScope.launch {
           mutableData =  signupRepository?.signupUser(user)
        }
    }

    fun doSignIn(user : User){
        viewModelScope.launch {
            signinData =  signupRepository?.signInUser(user)
        }
    }

    fun getData () : MutableLiveData<Resource<SignupResponse>>?{
        return mutableData
    }

    fun getSignInData () : MutableLiveData<Resource<SignupResponse>>?{
        return signinData
    }
}