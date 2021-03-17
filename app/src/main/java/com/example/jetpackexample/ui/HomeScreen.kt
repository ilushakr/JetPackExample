package com.example.jetpackexample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.jetpackexample.entities.FullPost
import com.example.jetpackexample.entities.Post
import com.example.jetpackexample.main.MainActivityViewModel
import com.example.jetpackexample.navDependencies.Destinations.FullPostDestination
import com.example.jetpackexample.utils.DEFAULT_IMAGE
import com.example.jetpackexample.utils.fullPostToJson
import com.example.jetpackexample.utils.loadPicture


@Composable
fun HomeScreen(navController: NavController, viewModel: MainActivityViewModel, posts: List<Post>) {

    Column(modifier = Modifier.fillMaxWidth()) {

        TopAppBar(
            title = {
                Text(text = "Posts")
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            elevation = 2.dp
        )

        LazyColumn(modifier = Modifier
            .padding(start = 6.dp, end = 6.dp)
            .fillMaxWidth()){
            items(posts.size){r ->
                PostCard(post = posts[r], viewModel= viewModel, navController)
            }
        }


    }

}

@Composable
fun PostCard(post: Post, viewModel: MainActivityViewModel, navController: NavController){

    val user = viewModel.getUser(post.userId)

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 6.dp,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    "${FullPostDestination}/${
                        fullPostToJson(
                            FullPost(
                                userId = post.userId,
                                id = post.id,
                                title = post.title,
                                body = post.body,
                                name = user.value?.name ?: "Unknown",
                                username = user.value?.username ?: "Unknown"
                            )
                        )
                    }"
                )
            }
    ){
        Column {

            val image = loadPicture(
                url = "https://picsum.photos/300/200?temp=${post.id}",
                defaultImage = DEFAULT_IMAGE
            ).value

            image?.let {img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ){

                user.value?.let {
                    Text(
                        text = it.name,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(text = post.title)
                }

            }

        }
    }
}