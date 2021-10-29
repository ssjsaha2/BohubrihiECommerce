package com.example.bohubrihiecommerce.retrofit

import com.example.bohubrihiecommerce.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private var apiService: APIService? = null


    private fun getRetrofitInstance():Retrofit? {

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .addNetworkInterceptor(Interceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                try {
                    if (response.code == Constants.HTTP_STATUS_UNAUTHORIZED) {
                        ///todo///
                    }
                } catch (e: Exception) {

                }
                response
            })
            .readTimeout(120, TimeUnit.SECONDS).build()
        val BASE_URL: String = Constants.BASE_URL
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
    fun getAPIService(): APIService {
        if (apiService == null) {
            apiService = getRetrofitInstance()?.create(APIService::class.java)
        }
        return apiService!!
    }

}
