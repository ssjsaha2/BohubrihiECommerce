package com.example.bohubrihiecommerce.models

data class SignupResponse(val user : User? ,
                          val token : String? ,
                          val message : String? )