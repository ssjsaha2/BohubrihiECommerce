package com.example.bohubrihiecommerce.retrofit

import com.example.bohubrihiecommerce.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {


   @POST("api/user/signup")
    fun doSignUp(
        @Body user: User?
    ): Call<SignupResponse>


    @POST("api/user/signin")
    fun doSignin(
        @Body user: User?
    ): Call<SignupResponse>

    @GET("api/category")
    fun getCategories(
    ): Call<ArrayList<Category>>

    @GET("api/product")
    fun getProducts(
        @Query("order") order : String?,
        @Query("sortBy") sortBy : String?,
        @Query("limit")limit : Int
    ):Call<ArrayList<Product>>


    @GET("api/product/{id}")
    fun getSingleProduct(
        @Path("id")path : String
    ) : Call<Product>

    @GET("api/profile")
    fun getProfile(
        @Header("Authorization") token : String,
    ) : Call<Profile>
    @POST("api/profile")
    fun updateProfile(
        @Header("Authorization") token : String,
        @Body profile : Profile
    ) : Call<ResponseBody>

    @GET("api/product/photo/{id}")
    fun downloadImage(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ) : Call<ResponseBody>
}