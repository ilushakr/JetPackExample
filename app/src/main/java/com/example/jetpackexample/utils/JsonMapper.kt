package com.example.jetpackexample.utils

import com.example.jetpackexample.entities.FullPost
import com.example.jetpackexample.entities.Post
import com.google.gson.Gson

fun jsonToFullPost(stringValue: String): FullPost {
    return  Gson().fromJson(stringValue, FullPost::class.java)
}

fun fullPostToJson(fullPost: FullPost): String {
    return  Gson().toJson(fullPost)
}