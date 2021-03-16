package com.example.jetpackexample.ui

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.jetpackexample.entities.FullPost

@Composable
fun FullPost(fullPost: FullPost, goBack: () -> Unit) {

    Log.d("tag", fullPost.toString())
    Button(onClick = goBack ) {
        Text(text = "Navigate one")
    }

}