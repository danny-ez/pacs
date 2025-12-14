package com.example.pacs.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(onBack: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Acerca de") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("App offline de soporte clínico para pancreatitis aguda. No reemplaza el juicio clínico.")
            Text("Modo rápido calcula lo disponible aun con datos faltantes.")
            Text("No recolecta analytics ni requiere conexión.")
        }
    }
}
