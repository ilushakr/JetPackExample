package com.example.jetpackexample.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.layout.Gravity
import com.example.jetpackexample.entities.FullPost
import com.example.jetpackexample.utils.DEFAULT_IMAGE
import com.example.jetpackexample.utils.loadPicture

@Composable
fun FullPost(fullPost: FullPost, goBack: () -> Unit) {

    
    Column(modifier = Modifier.fillMaxWidth()) {

        TopAppBar(
            title = {
                Text(text = fullPost.username)
            },
            navigationIcon = {
                IconButton(onClick = goBack) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Menu Btn",
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            elevation = 2.dp
        )

        LazyColumn{
            item {
                val image = loadPicture(
                    url = "https://picsum.photos/300/200?temp=${fullPost.id}",
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
                        .padding(6.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {

                    SelectionContainer {
                        Text(
                            text = fullPost.title,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    SelectionContainer {
                        Text(
                            text = fullPost.body,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(),
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 30.dp))

                    SelectionContainer {
                        Row {

                            DisableSelection {
                                Text(
                                    text = "Author: ",
                                    maxLines = 1,
                                )
                            }

                            Text(
                                text = fullPost.name,
                                maxLines = 1,
                            )
                        }

                    }

                }
            }
        }

    }

}
