package com.example.pacs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.pacs.ui.screens.AboutScreen
import com.example.pacs.ui.screens.AssessmentFormScreen
import com.example.pacs.ui.screens.PatientDetailScreen
import com.example.pacs.ui.screens.PatientFormScreen
import com.example.pacs.ui.screens.PatientListScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = AppDestination.Patients.route, modifier = modifier) {
        composable(AppDestination.Patients.route) {
            PatientListScreen(
                onPatientSelected = { id -> navController.navigate("patientDetail/$id") },
                onNewPatient = { navController.navigate(AppDestination.PatientForm.route) },
                onAbout = { navController.navigate(AppDestination.About.route) }
            )
        }
        composable(AppDestination.PatientForm.route) {
            PatientFormScreen(onDone = { navController.popBackStack() })
        }
        composable("patientDetail/{patientId}") {
            PatientDetailScreen(
                onNewAssessment = { id -> navController.navigate("assessmentForm/$id") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("assessmentForm/{patientId}") {
            AssessmentFormScreen(onBack = { navController.popBackStack() })
        }
        composable(AppDestination.About.route) {
            AboutScreen(onBack = { navController.popBackStack() })
        }
    }
}
