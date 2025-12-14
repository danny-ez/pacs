package com.example.pacs.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PatientDetailScreen(onNewAssessment: (Long) -> Unit, onBack: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Detalle paciente") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Timeline de evaluaciones (demo).", style = MaterialTheme.typography.bodyLarge)
            Button(onClick = { onNewAssessment(1L) }) { Text("Nueva evaluación") }
            Button(onClick = onBack) { Text("Volver") }
        }
    }
}
