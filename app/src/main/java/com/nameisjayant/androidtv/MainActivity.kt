package com.nameisjayant.androidtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nameisjayant.androidtv.layout.SideDrawerScreen
import com.nameisjayant.androidtv.navigation.MainNavigation
import com.nameisjayant.androidtv.ui.theme.AndroidTvTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberAppState()
            AndroidTvTheme {
                Scaffold { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray.copy(alpha = 0.7f))
                    ) {
                        Row {
                            if (appState.shouldShowSideBar) {
                                SideDrawerScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    appState.navHostController
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                            }

                            MainNavigation(navHostController = appState.navHostController)
                        }
                    }
                }
            }
        }
    }
}