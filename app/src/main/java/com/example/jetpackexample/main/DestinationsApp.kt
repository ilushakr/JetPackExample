package com.example.jetpackexample.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.jetpackexample.ui.HomeScreen
import com.example.jetpackexample.ui.FullPost
import com.example.jetpackexample.entities.Post
import com.example.jetpackexample.navDependencies.Actions
import com.example.jetpackexample.navDependencies.Destinations.FullPostDestination
import com.example.jetpackexample.navDependencies.Destinations.HomeDestination
import com.example.jetpackexample.utils.jsonToFullPost

@Composable
fun DestinationsApp(mainViewModel: MainActivityViewModel, posts: List<Post>) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
        NavHost(navController, startDestination = HomeDestination) {

            composable(HomeDestination) {
                HomeScreen(navController, mainViewModel, posts)
            }

            composable(
                route = "$FullPostDestination/{fullPost}",
                arguments = listOf(
                    navArgument("fullPost") {
                        type = NavType.StringType
                    }
                )
            ){ backStackEntry ->
                FullPost(
                    fullPost= jsonToFullPost(
                        backStackEntry.arguments?.getString("fullPost")!!
                    ),
                    goBack = actions.goBack
                )
            }

    }
}
