package com.example.pacs.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class VitalsInput(
    val heartRate: Int? = null,
    val respiratoryRate: Int? = null,
    val temperatureC: Double? = null,
    val systolicBP: Int? = null,
    val glasgowComaScore: Int? = null
)

@Serializable
data class LabInput(
    val wbc: Double? = null,
    val bun: Double? = null,
    val bunUnit: ConcentrationUnit? = null,
    val urea: Double? = null,
    val ureaUnit: ConcentrationUnit? = null,
    val creatinine: Double? = null,
    val platelets: Int? = null,
    val bilirubin: Double? = null,
    val pao2: Double? = null,
    val fio2: Double? = null,
    val sodium: Double? = null,
    val potassium: Double? = null,
    val hematocrit: Double? = null,
    val ph: Double? = null
)

@Serializable
data class ClinicalInput(
    val pleuralEffusion: Boolean? = null,
    val alteredMentalStatus: Boolean? = null,
    val ageYears: Int? = null,
    val vasopressor: String? = null,
    val vasopressorDose: Double? = null
)

@Serializable
data class AssessmentInputs(
    val vitals: VitalsInput = VitalsInput(),
    val lab: LabInput = LabInput(),
    val clinical: ClinicalInput = ClinicalInput()
)
