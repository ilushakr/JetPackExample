package com.example.jetpackexample.api

import com.example.jetpackexample.entities.Post
import com.example.jetpackexample.entities.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {

    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

}