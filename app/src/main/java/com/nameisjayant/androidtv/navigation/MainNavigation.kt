package com.nameisjayant.androidtv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nameisjayant.androidtv.layout.MenuItem
import com.nameisjayant.androidtv.screens.login.LoginScreen
import com.nameisjayant.androidtv.screens.main.HomeScreen
import com.nameisjayant.androidtv.screens.main.MusicScreen
import com.nameisjayant.androidtv.screens.main.SearchScreen
import com.nameisjayant.androidtv.screens.main.VideoScreen


@Composable
fun MainNavigation(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = ScreenDestination.Login.route) {
        navigation(
            startDestination = MenuItem.Home.route,
            route = ScreenDestination.SideNav.route
        ) {
            composable(MenuItem.Home.route) {
                HomeScreen()
            }
            composable(MenuItem.Search.route) {
                SearchScreen()
            }
            composable(MenuItem.Video.route) {
                VideoScreen()
            }
            composable(MenuItem.Music.route) {
                MusicScreen()
            }
        }
        composable(ScreenDestination.Login.route) {
            LoginScreen(navHostController = navHostController)
        }
    }

}

sealed class ScreenDestination(val route: String) {

    data object SideNav : ScreenDestination("/sideNav")
    data object Login : ScreenDestination("/login")

}