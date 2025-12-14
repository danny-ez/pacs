package com.example.pacs.domain.usecase

import com.example.pacs.domain.model.AssessmentInputs
import com.example.pacs.domain.model.BunProvenance
import com.example.pacs.domain.model.DerivedValues
import com.example.pacs.domain.model.ScoreResult
import com.example.pacs.domain.model.ScoreStatus

object BisapCalculator {
    data class BisapContext(
        val result: ScoreResult<Int>,
        val derivedValues: DerivedValues
    )

    fun calculate(inputs: AssessmentInputs): BisapContext {
        val missing = mutableListOf<String>()
        var score = 0
        val bunResolution = BunResolver.resolve(inputs.lab)
        val sirs = SirsCalculator.calculate(inputs)

        val age = inputs.clinical.ageYears
        if (age == null) missing += "edad" else if (age >= 60) score++

        if (bunResolution.provenance == BunProvenance.MISSING || bunResolution.bunMgDl == null) {
            missing += "BUN/urea"
        } else if (bunResolution.bunMgDl > 25) {
            score++
        }

        when (inputs.clinical.pleuralEffusion) {
            true -> score++
            null -> missing += "derrame pleural"
        }

        when (inputs.clinical.alteredMentalStatus) {
            true -> score++
            null -> missing += "estado mental"
        }

        if (sirs.value != null && sirs.value >= 2) {
            score++
        } else if (sirs.status == ScoreStatus.MISSING) {
            missing += "SIRS"
        }

        val warnings = mutableListOf<String>()
        if (bunResolution.provenance == BunProvenance.DERIVED_FROM_UREA) {
            warnings += "BUN derivado de urea"
        }
        if (bunResolution.provenance == BunProvenance.MISSING) {
            warnings += "BISAP incompleto por falta de BUN/urea"
        }

        val status = when {
            bunResolution.provenance == BunProvenance.MISSING -> ScoreStatus.PARTIAL
            missing.isEmpty() -> ScoreStatus.COMPLETE
            else -> ScoreStatus.PARTIAL
        }

        val interpretation = when {
            status == ScoreStatus.PARTIAL && bunResolution.provenance == BunProvenance.MISSING ->
                "Falta BUN/urea para BISAP"
            score <= 2 -> "Riesgo bajo"
            else -> "Riesgo aumentado"
        }

        val result = ScoreResult(
            value = if (bunResolution.provenance == BunProvenance.MISSING) null else score,
            status = status,
            missingFields = missing,
            warnings = warnings,
            interpretation = interpretation
        )

        return BisapContext(result = result, derivedValues = DerivedValues(bun = bunResolution))
    }
}
