package com.example.pacs.domain.usecase

import com.example.pacs.domain.model.AssessmentInputs
import com.example.pacs.domain.model.ScoreResult
import com.example.pacs.domain.model.ScoreStatus

object SofaCalculator {
    fun calculate(inputs: AssessmentInputs): ScoreResult<Map<String, Int>> {
        val missing = listOf("Tabla detallada pendiente de completar")
        return ScoreResult(
            value = emptyMap(),
            status = ScoreStatus.PARTIAL,
            missingFields = missing,
            interpretation = "Estructura lista para completar tablas SOFA"
        )
    }
}
