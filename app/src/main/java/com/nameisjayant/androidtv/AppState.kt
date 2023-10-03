package com.nameisjayant.androidtv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nameisjayant.androidtv.layout.MenuItem


@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController()
) =
    remember(navHostController) {
        AppState(navHostController)
    }


@Stable
class AppState(
    val navHostController: NavHostController
) {

    private val sideNavRoutes = MenuItem.values().map { it.route }

    val shouldShowSideBar: Boolean
        @Composable get() = navHostController
            .currentBackStackEntryAsState().value?.destination?.route in sideNavRoutes

}