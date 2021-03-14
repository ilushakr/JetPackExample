package com.example.jetpackexample.main

import com.example.jetpackexample.api.Api
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {

    fun getVersion() = api.getPosts()

    fun getUser(id: Int) = api.getUser(id)

}