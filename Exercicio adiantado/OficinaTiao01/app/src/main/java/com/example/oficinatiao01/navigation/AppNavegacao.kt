package com.example.oficinatiao01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.oficinatiao01.TelaPrincipal
import com.example.oficinatiao01.ui.screens.TelaCadastroPlaca

@Composable
fun AppNavegacao() {
    val navController = rememberNavController()

    NavHost(
        navController = navController
    )
}