package com.example.bohubrihiecommerce.models

import com.google.gson.annotations.SerializedName

data class Product(
    val sold : Int,

    @SerializedName(value = "_id")
    val id : String,
    val name : String,
    val category : Category?,
    val quantity : Int,
    val price : Float,
    val description : String?
)
