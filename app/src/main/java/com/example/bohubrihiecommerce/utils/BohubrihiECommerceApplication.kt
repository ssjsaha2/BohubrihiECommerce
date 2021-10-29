package com.example.bohubrihiecommerce.utils

import androidx.multidex.MultiDexApplication

class BohubrihiECommerceApplication : MultiDexApplication()  {

    override fun onCreate() {
        super.onCreate()
        SharedPrefUtil.init(applicationContext)
    }
}