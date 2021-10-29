package com.example.bohubrihiecommerce.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.bohubrihiecommerce.R

object SharedPrefUtil {

    private var editor: SharedPreferences.Editor? = null
    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun saveToken(token : String?){
        editor?.putString(Constants.TOKEN , token)?.apply()
    }

    fun getToken() : String?{
        return sharedPreferences?.getString(Constants.TOKEN , null)
    }
}