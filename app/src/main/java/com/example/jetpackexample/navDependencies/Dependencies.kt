package com.example.jetpackexample.navDependencies

import androidx.navigation.NavHostController

object Destinations {
    const val HomeDestination = "home"
    const val FullPostDestination = "fullPost"
}

class Actions(navController: NavHostController) {

    val goBack: () -> Unit = {
        navController.popBackStack()
    }

}