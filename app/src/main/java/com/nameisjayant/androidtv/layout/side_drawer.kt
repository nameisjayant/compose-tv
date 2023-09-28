package com.nameisjayant.androidtv.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nameisjayant.androidtv.R

data class MenuItem(
    val id: Int, val item: String, @DrawableRes val icon: Int
)

val itemList = listOf(
    MenuItem(
        1, "Home", R.drawable.baseline_home_24
    ), MenuItem(
        1, "Search", R.drawable.baseline_search_24
    ), MenuItem(
        1, "Videos", R.drawable.baseline_video_call_24
    ), MenuItem(
        1, "Music", R.drawable.baseline_music_note_24
    )
)


@Composable
fun SideDrawerScreen(
    modifier: Modifier = Modifier
) {
    var selectedItems by remember { mutableStateOf(itemList[0].id) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        Column {
            itemList.forEach {
                NavigationRow(
                    menuItem = it,
                    isSelected = selectedItems == it.id,
                    onMenuSelected = { item ->
                        selectedItems = item.id
                    })
            }
        }
    }, modifier = modifier) {

    }

}

@Composable
fun NavigationRow(
    modifier: Modifier = Modifier,
    menuItem: MenuItem,
    isSelected: Boolean,
    onMenuSelected: (MenuItem) -> Unit
) {

    IconButton(
        onClick = {
            onMenuSelected(menuItem)
        },
        modifier = modifier
            .padding(10.dp)
            .size(24.dp),
    ) {
        Icon(
            painter = painterResource(id = menuItem.icon),
            contentDescription = null,
            tint = if (isSelected) Color.White else Color.Unspecified
        )
    }

}