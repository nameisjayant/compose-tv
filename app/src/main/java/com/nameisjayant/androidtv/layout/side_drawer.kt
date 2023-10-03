package com.nameisjayant.androidtv.layout

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nameisjayant.androidtv.R

enum class MenuItem(val route: String, @DrawableRes val icon: Int) {
    Home("/home", R.drawable.baseline_home_24),
    Search("/search", R.drawable.baseline_search_24),
    Video("/video", R.drawable.baseline_video_call_24),
    Music("/music", R.drawable.baseline_music_note_24)
}


@Composable
fun SideDrawerScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val menuItemList = listOf(
        MenuItem.Home,
        MenuItem.Search,
        MenuItem.Video,
        MenuItem.Music
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationRail(
        modifier = modifier, containerColor = Color.Gray.copy(alpha = 0.4f)
    ) {
        menuItemList.forEach { screen ->
            NavigationRow(menuItem = screen,
                isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onMenuSelected = { item ->
                    if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    } else {
                        navHostController.navigate(item.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }

}

@Composable
fun NavigationRow(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    isSelected: Boolean,
    onMenuSelected: (MenuItem) -> Unit
) {

    val tint by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Unspecified,
        label = stringResource(R.string.animatedcolor)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 12.dp)
            .toggleable(
                value = false,
                onValueChange = { onMenuSelected(menuItem) },
                role = Role.Button,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
    ) {
        Icon(
            painter = painterResource(id = menuItem.icon),
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.height(5.dp))
        AnimatedVisibility(visible = isSelected) {
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .width(15.dp)
                    .height(4.dp)
            )
        }
    }

}