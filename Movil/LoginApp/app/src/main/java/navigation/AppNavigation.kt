package com.example.loginapp.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.loginapp.screens.*

@Composable

fun AppNavigation(){
    val navController = rememberNavController()
    var diasCotizados by remember {mutableStateOf(0)}

    NavHost(navController, startDestination = "login"){
        composable("login"){
            LoginScreen{
                navController.navigate("panel")
            }
        }
        composable("panel"){
            PanelEstudianteScreen(
                diasCotizados = diasCotizados,
                onAsistenciaClick = { navController.navigate("asistencia")},
                onLogout = {
                    navController.navigate("login"){
                        popUpTo ("login") {inclusive = true}
                    }
                }
            )
        }

        composable("asistencia"){
            AsistenciaScreen(
                onAsistencia = {
                    diasCotizados++ //suma dia cotizado
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
        }
    }
