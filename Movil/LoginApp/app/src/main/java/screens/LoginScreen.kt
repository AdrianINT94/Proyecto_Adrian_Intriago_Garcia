package com.example.loginapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit){

    var usuario by remember {mutableStateOf("")}
    var contrasena by remember {mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Login", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        TextField(usuario,{usuario = it}, label = {Text("Usuario")})

        Spacer(Modifier.height(16.dp))

        TextField(value = contrasena, onValueChange = {contrasena = it }, label = {Text("Contraseña")})

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            if (usuario.isNotBlank() && contrasena.isNotBlank()){
                onLoginSuccess()
            }
        }){
            Text("Entrar")
        }
    }
}