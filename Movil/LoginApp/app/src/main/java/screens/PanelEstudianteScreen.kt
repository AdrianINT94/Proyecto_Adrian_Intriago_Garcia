package com.example.loginapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun PanelEstudianteScreen(
    diasCotizados: Int,
    onAsistenciaClick: () -> Unit,
    onLogout: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Panel del estudiante" , style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Text("Nombre:  Juan Perez")
        Text("Empresa: Empresa  S.L")
        Text("Tutor empresa :Maria Lopez")
        Text("Tutor docente : Carlos Garcia")

        Spacer(Modifier.height(24.dp))

        Text("Dias cotizados este mes : $diasCotizados")

        Spacer (Modifier.height(24.dp))

        Button(onClick = onAsistenciaClick){
            Text ("Gestionar asistencia")

        }

        Spacer(Modifier.height(32.dp))

        Button(onClick = onLogout){
            Text("Cerrar sesion")
        }


    }
}
