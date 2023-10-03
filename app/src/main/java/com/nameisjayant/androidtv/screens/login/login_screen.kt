package com.nameisjayant.androidtv.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nameisjayant.androidtv.R
import com.nameisjayant.androidtv.navigation.ScreenDestination


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.login), style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextFieldLayout(
            value = username,
            label = stringResource(R.string.username),
            onValueChange = { username = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextFieldLayout(
            value = password,
            label = stringResource(R.string.password),
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navHostController.navigate(ScreenDestination.SideNav.route)
        }, shape = CircleShape) {
            Text(
                text = stringResource(id = R.string.login), style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }

}

@Composable
fun AppTextFieldLayout(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(value = value, onValueChange = onValueChange, modifier = modifier, label = {
        Text(text = label)
    })
}