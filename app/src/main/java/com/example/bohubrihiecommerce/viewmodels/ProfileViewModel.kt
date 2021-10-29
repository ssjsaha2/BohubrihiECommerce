package com.example.bohubrihiecommerce.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bohubrihiecommerce.models.Profile
import com.example.bohubrihiecommerce.repositories.ProfileRepository
import com.example.bohubrihiecommerce.utils.Resource
import okhttp3.ResponseBody

class ProfileViewModel : ViewModel() {

    private var profileGetLiveData : MutableLiveData<Resource<Profile>>
    private var profileUpdateLiveData : MutableLiveData<Resource<ResponseBody>>

    init {
        profileGetLiveData = MutableLiveData()
        profileUpdateLiveData = MutableLiveData()
    }

    fun getProfile(token : String){
       profileGetLiveData =  ProfileRepository.getProfile(token)
    }

    fun getProfileGetLiveData() : MutableLiveData<Resource<Profile>>{
        return profileGetLiveData
    }


    fun updateProfile(token : String , profile : Profile){
       profileUpdateLiveData =  ProfileRepository.updateProfile(token , profile)
    }

    fun getProfileUpdateLiveData() : MutableLiveData<Resource<ResponseBody>>{
        return profileUpdateLiveData
    }
}

