package com.example.bohubrihiecommerce.utils

import android.graphics.Bitmap
import com.example.bohubrihiecommerce.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.BitmapFactory
import java.io.InputStream


object ImageDownloader {
    fun downloadImage(productId : String , imageDownloadListener: ImageDownloadListener){
        TokenManager.token?.let {
            RetrofitClient.getAPIService().downloadImage(it ,productId).enqueue(
                object :Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if(response.isSuccessful){
                            try {
                                val inputStream: InputStream = response.body()?.byteStream()!!
                                val bitmap = BitmapFactory.decodeStream(inputStream)
                                imageDownloadListener.onImageDownload(bitmap)
                            }catch ( e: Exception){
                                e.printStackTrace()
                                imageDownloadListener.onImageDownload(null)

                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        imageDownloadListener.onImageDownload(null)
                    }

                }
            )
        }
    }
}