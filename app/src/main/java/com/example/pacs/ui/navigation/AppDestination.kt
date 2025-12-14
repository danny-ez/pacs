package com.example.pacs.ui.navigation

enum class AppDestination(val route: String) {
    Patients("patients"),
    PatientForm("patientForm"),
    AssessmentForm("assessmentForm/{patientId}"),
    PatientDetail("patientDetail/{patientId}"),
    About("about")
}
