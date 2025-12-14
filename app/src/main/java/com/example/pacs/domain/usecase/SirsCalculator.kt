package com.example.pacs.domain.usecase

import com.example.pacs.domain.model.AssessmentInputs
import com.example.pacs.domain.model.ScoreResult
import com.example.pacs.domain.model.ScoreStatus

object SirsCalculator {
    fun calculate(inputs: AssessmentInputs): ScoreResult<Int> {
        val missing = mutableListOf<String>()
        var criteriaMet = 0

        val temp = inputs.vitals.temperatureC
        val heartRate = inputs.vitals.heartRate
        val respiratoryRate = inputs.vitals.respiratoryRate
        val wbc = inputs.lab.wbc

        if (temp == null) missing += "temperatura"
        else if (temp > 38 || temp < 36) criteriaMet++

        if (heartRate == null) missing += "frecuencia cardiaca"
        else if (heartRate > 90) criteriaMet++

        if (respiratoryRate == null) missing += "frecuencia respiratoria"
        else if (respiratoryRate > 20) criteriaMet++

        if (wbc == null) missing += "WBC"
        else if (wbc > 12000 || wbc < 4000) criteriaMet++

        val status = when {
            missing.isEmpty() -> ScoreStatus.COMPLETE
            missing.size < 4 -> ScoreStatus.PARTIAL
            else -> ScoreStatus.MISSING
        }

        val interpretation = "Criterios SIRS: $criteriaMet"

        return ScoreResult(
            value = criteriaMet,
            status = status,
            missingFields = missing,
            interpretation = interpretation
        )
    }
}
