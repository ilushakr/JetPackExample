package com.example.jetpackexample.main

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackexample.entities.Post
import com.example.jetpackexample.utils.DEFAULT_IMAGE
import com.example.jetpackexample.utils.loadPicture
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var factory : MainActivityViewModelFactory
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        viewModel.getPosts().observe(this, Observer<List<Post>?> { posts ->
            posts.let {
                setContent {
                    LazyColumn(modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp)
                        .fillMaxWidth()){
                        items(it){post ->
                            PostCard(post = post)
                        }
                    }
                }
            }
        })

    }

    @Composable
    fun PostCard(post: Post){
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = 6.dp,
            modifier = Modifier
                .padding(bottom = 6.dp, top = 6.dp)
                .fillMaxWidth()
                .clickable { Log.d("tag", post.id.toString()) }
        ){
            Column {

                val image = loadPicture(url = "https://picsum.photos/300/200?temp=${post.id}", defaultImage = DEFAULT_IMAGE).value
                image?.let {img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Row(modifier = Modifier.padding(6.dp)) {
                    Text(text = post.title)

                }

            }
        }
    }
}
