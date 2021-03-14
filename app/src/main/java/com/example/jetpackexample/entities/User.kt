package com.example.jetpackexample.entities

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String,
)