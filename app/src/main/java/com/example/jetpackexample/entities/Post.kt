package com.example.jetpackexample.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class Post(
    @SerializedName("userId") val userId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("body") val body : String
)