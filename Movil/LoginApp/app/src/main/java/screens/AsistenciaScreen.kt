package com.example.loginapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AsistenciaScreen(
    onAsistencia: () ->Unit,
    onVolver: () ->Unit
) {
    var jornada by remember { mutableStateOf("Presencial") }
    var estado by remember { mutableStateOf("No registrado") }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Gestion de asistencia",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(24.dp))


        Text("Tipo de jornada")

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {


            RadioButton(
                selected = jornada == "Teletrabajo",
                onClick = { jornada = "Teletrabajo" }
            )
            Text("Teletrabajo")


            Spacer(Modifier.height(24.dp))

            RadioButton(
                selected = jornada == "Presencial",
                onClick = { jornada = "Presencial" }
            )
            Text("Presencial")
        }

        Text("Estado : $estado")

        Spacer(Modifier.height(24.dp))

        Row {


            Button(onClick = {
                estado = "Asistencia registrada"
                onAsistencia()
            }) {
                Text("Asistencia")
            }

            Spacer(Modifier.width(16.dp))

            Button(onClick = {
                estado = "Ausencia registrada"
            }) {
                Text("Ausencia")
            }
        }

        Spacer(Modifier.height(32.dp))

        Text(
            "Registro conforme al control de cotizacion y Seguridad social",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(32.dp))

        Button(onClick = onVolver) {
            Text("Volver")
        }
    }

}

