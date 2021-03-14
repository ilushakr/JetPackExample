package com.example.jetpackexample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackexample.entities.Post
import com.example.jetpackexample.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val posts = MutableLiveData<List<Post>?>()
    private val user = MutableLiveData<User?>()

    fun getPosts(): LiveData<List<Post>?>{

        repository.getVersion().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                posts.value = null
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                posts.value = response.body()
            }

        })

        return posts
    }

    fun getUser(id: Int): LiveData<User?>{

        repository.getUser(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                user.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

        return user
    }

}