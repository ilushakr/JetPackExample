package com.example.jetpackexample.entities

import com.google.gson.annotations.SerializedName

data class FullPost(
    @SerializedName("userId") val userId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("body") val body : String,
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String
)