package com.example.pacs.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PatientFormScreen(onDone: () -> Unit) {
    var alias by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("Alta paciente") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(value = alias, onValueChange = { alias = it }, label = { Text("Alias / nombre") })
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notas") })
            Button(onClick = onDone, modifier = Modifier.padding(top = 16.dp)) { Text("Guardar") }
        }
    }
}
