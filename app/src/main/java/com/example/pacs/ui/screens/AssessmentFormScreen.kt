package com.example.pacs.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.pacs.domain.model.AssessmentInputs
import com.example.pacs.domain.model.ConcentrationUnit
import com.example.pacs.domain.usecase.BisapCalculator
import com.example.pacs.domain.usecase.MarshallCalculator
import com.example.pacs.domain.usecase.SirsCalculator

@Composable
fun AssessmentFormScreen(onBack: () -> Unit) {
    var vitalsExpanded by remember { mutableStateOf(true) }
    var labExpanded by remember { mutableStateOf(true) }
    var clinicalExpanded by remember { mutableStateOf(true) }

    var bunValue by remember { mutableStateOf(TextFieldValue("")) }
    var bunUnit by remember { mutableStateOf(ConcentrationUnit.MG_DL) }
    var ureaValue by remember { mutableStateOf(TextFieldValue("")) }
    var ureaUnit by remember { mutableStateOf(ConcentrationUnit.MG_DL) }
    var heartRate by remember { mutableStateOf(TextFieldValue("")) }
    var respiratoryRate by remember { mutableStateOf(TextFieldValue("")) }
    var temperature by remember { mutableStateOf(TextFieldValue("")) }
    var wbc by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var pleural by remember { mutableStateOf(false) }
    var alteredMental by remember { mutableStateOf(false) }

    var results by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(title = { Text("Nueva evaluación") })
        ExpandableSection(title = "Signos vitales", expanded = vitalsExpanded, onToggle = { vitalsExpanded = !vitalsExpanded }) {
            OutlinedTextField(
                value = heartRate,
                onValueChange = { heartRate = it },
                label = { Text("FC") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = respiratoryRate,
                onValueChange = { respiratoryRate = it },
                label = { Text("FR") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = temperature,
                onValueChange = { temperature = it },
                label = { Text("Temperatura °C") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        ExpandableSection(title = "Laboratorio", expanded = labExpanded, onToggle = { labExpanded = !labExpanded }) {
            Text("Podemos derivar BUN desde urea. No se puede derivar desde creatinina.", style = MaterialTheme.typography.bodySmall)
            OutlinedTextField(
                value = bunValue,
                onValueChange = { bunValue = it },
                label = { Text("BUN") },
                modifier = Modifier.fillMaxWidth()
            )
            UnitSelector(selected = bunUnit, onUnitSelected = { bunUnit = it }, label = "Unidad BUN")
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            OutlinedTextField(
                value = ureaValue,
                onValueChange = { ureaValue = it },
                label = { Text("Urea") },
                modifier = Modifier.fillMaxWidth()
            )
            UnitSelector(selected = ureaUnit, onUnitSelected = { ureaUnit = it }, label = "Unidad urea")
            OutlinedTextField(
                value = wbc,
                onValueChange = { wbc = it },
                label = { Text("WBC") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        ExpandableSection(title = "Clínicos / Soporte", expanded = clinicalExpanded, onToggle = { clinicalExpanded = !clinicalExpanded }) {
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth()
            )
            ToggleRow(label = "Derrame pleural", value = pleural, onChange = { pleural = it })
            ToggleRow(label = "Estado mental alterado", value = alteredMental, onChange = { alteredMental = it })
        }

        Button(onClick = {
            val inputs = AssessmentInputs(
                vitals = com.example.pacs.domain.model.VitalsInput(
                    heartRate = heartRate.text.toIntOrNull(),
                    respiratoryRate = respiratoryRate.text.toIntOrNull(),
                    temperatureC = temperature.text.toDoubleOrNull()
                ),
                lab = com.example.pacs.domain.model.LabInput(
                    bun = bunValue.text.toDoubleOrNull(),
                    bunUnit = bunUnit,
                    urea = ureaValue.text.toDoubleOrNull(),
                    ureaUnit = ureaUnit,
                    wbc = wbc.text.toDoubleOrNull()
                ),
                clinical = com.example.pacs.domain.model.ClinicalInput(
                    ageYears = age.text.toIntOrNull(),
                    pleuralEffusion = pleural,
                    alteredMentalStatus = alteredMental
                )
            )

            val sirs = SirsCalculator.calculate(inputs)
            val bisap = BisapCalculator.calculate(inputs)
            val marshall = MarshallCalculator.calculate(inputs)

            results = listOf(
                "SIRS: ${sirs.value ?: "-"} (${sirs.status})",
                "BISAP: ${bisap.result.value ?: "-"} (${bisap.result.status})",
                "Marshall: ${marshall.value} (${marshall.status})"
            ) + bisap.result.warnings
        }, modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Calcular todo")
        }

        results.forEach { line ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)) {
                Text(line, modifier = Modifier.padding(12.dp))
            }
        }

        Button(onClick = onBack, modifier = Modifier.padding(top = 8.dp)) { Text("Volver") }
    }
}

@Composable
private fun ExpandableSection(title: String, expanded: Boolean, onToggle: () -> Unit, content: @Composable () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(bottom = 8.dp)) {
                Text(title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                IconButton(onClick = onToggle) { Icon(if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null) }
            }
            if (expanded) {
                content()
            }
        }
    }
}

@Composable
private fun UnitSelector(selected: ConcentrationUnit, onUnitSelected: (ConcentrationUnit) -> Unit, label: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Text(label, modifier = Modifier.weight(1f))
        Button(onClick = { onUnitSelected(ConcentrationUnit.MG_DL) }, modifier = Modifier.padding(end = 4.dp)) {
            Text("mg/dL" + if (selected == ConcentrationUnit.MG_DL) " ✓" else "")
        }
        Button(onClick = { onUnitSelected(ConcentrationUnit.MMOL_L) }) {
            Text("mmol/L" + if (selected == ConcentrationUnit.MMOL_L) " ✓" else "")
        }
    }
}

@Composable
private fun ToggleRow(label: String, value: Boolean, onChange: (Boolean) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable { onChange(!value) }
        .padding(vertical = 8.dp)) {
        Text(label, modifier = Modifier.weight(1f))
        Button(onClick = { onChange(!value) }) { Text(if (value) "Sí" else "No") }
    }
}
