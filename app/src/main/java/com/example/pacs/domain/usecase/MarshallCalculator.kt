package com.example.pacs.domain.usecase

import com.example.pacs.domain.model.AssessmentInputs
import com.example.pacs.domain.model.ScoreResult
import com.example.pacs.domain.model.ScoreStatus

object MarshallCalculator {
    data class Component(val respiratory: Int?, val renal: Int?, val cardiovascular: Int?)

    fun calculate(inputs: AssessmentInputs): ScoreResult<Component> {
        val missing = mutableListOf<String>()

        val pao2 = inputs.lab.pao2
        val fio2 = inputs.lab.fio2
        val creatinine = inputs.lab.creatinine
        val systolic = inputs.vitals.systolicBP

        val respiratoryScore = if (pao2 != null && fio2 != null && fio2 > 0) {
            val ratio = pao2 / fio2
            when {
                ratio >= 300 -> 0
                ratio >= 201 -> 1
                ratio >= 101 -> 2
                ratio >= 61 -> 3
                else -> 4
            }
        } else {
            missing += "PaO2/FiO2"
            null
        }

        val renalScore = creatinine?.let {
            when {
                it < 134 -> 0
                it < 168 -> 1
                it < 310 -> 2
                it < 442 -> 3
                else -> 4
            }
        } ?: run {
            missing += "creatinina"
            null
        }

        val cardiovascularScore = systolic?.let {
            when {
                it >= 90 -> 0
                it in 71..89 -> 1
                else -> 2
            }
        } ?: run {
            missing += "presión arterial sistólica"
            null
        }

        val component = Component(respiratoryScore, renalScore, cardiovascularScore)
        val nonNulls = listOf(respiratoryScore, renalScore, cardiovascularScore).filterNotNull()
        val status = when {
            missing.size >= 3 -> ScoreStatus.MISSING
            missing.isNotEmpty() -> ScoreStatus.PARTIAL
            else -> ScoreStatus.COMPLETE
        }

        val interpretation = if (nonNulls.any { it >= 2 }) {
            "Falla orgánica"
        } else {
            "Sin falla orgánica"
        }

        return ScoreResult(
            value = component,
            status = status,
            missingFields = missing,
            interpretation = interpretation
        )
    }
}
