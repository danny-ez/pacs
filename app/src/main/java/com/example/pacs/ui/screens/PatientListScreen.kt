package com.example.pacs.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PatientListScreen(
    onPatientSelected: (Long) -> Unit,
    onNewPatient: () -> Unit,
    onAbout: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Pacientes") },
            actions = {
                IconButton(onClick = onAbout) { Icon(Icons.Default.Info, contentDescription = "Acerca de") }
                IconButton(onClick = onNewPatient) { Icon(Icons.Default.Add, contentDescription = "Nuevo paciente") }
            }
        )
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Lista de pacientes (demo). Usa el botón + para crear uno nuevo.",
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = { onPatientSelected(1L) }) {
                Text("Abrir paciente de ejemplo")
            }
        }
    }
}
