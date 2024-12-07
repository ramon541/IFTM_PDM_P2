package com.iftm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iftm.ui.theme.IftmTheme

import com.iftm.view.ListaCafes
import com.iftm.view.SalvarCafe

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IftmTheme {
                val navController = rememberNavController()
                NavigationHost(navController = navController)
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "listaCafes") {
        composable("listaCafes") { ListaCafes(navController) }
        composable("salvarCafe") { SalvarCafe(navController) }
    }
}