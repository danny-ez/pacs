package com.example.pacs.domain

import com.example.pacs.domain.model.ConcentrationUnit
import com.example.pacs.domain.model.LabInput
import com.example.pacs.domain.usecase.BisapCalculator
import com.example.pacs.domain.usecase.BunResolver
import com.example.pacs.domain.model.AssessmentInputs
import com.example.pacs.domain.model.ClinicalInput
import com.example.pacs.domain.model.VitalsInput
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BunConversionTest {
    @Test
    fun `urea mgdl to bun`() {
        val resolution = BunResolver.resolve(LabInput(urea = 64.2, ureaUnit = ConcentrationUnit.MG_DL))
        assertEquals(64.2 / 2.14, resolution.bunMgDl!!, 0.001)
    }

    @Test
    fun `urea mmol to bun`() {
        val resolution = BunResolver.resolve(LabInput(urea = 10.0, ureaUnit = ConcentrationUnit.MMOL_L))
        assertEquals(28.0, resolution.bunMgDl!!, 0.001)
    }

    @Test
    fun `creatinine does not create bun`() {
        val resolution = BunResolver.resolve(LabInput(creatinine = 2.0))
        assertNull(resolution.bunMgDl)
    }

    @Test
    fun `bisap matches direct and derived bun`() {
        val inputsDirect = AssessmentInputs(
            lab = LabInput(bun = 30.0, bunUnit = ConcentrationUnit.MG_DL),
            clinical = ClinicalInput(ageYears = 65, pleuralEffusion = true, alteredMentalStatus = true),
            vitals = VitalsInput(temperatureC = 39.0, heartRate = 110, respiratoryRate = 24, systolicBP = 120)
        )
        val inputsDerived = inputsDirect.copy(
            lab = LabInput(urea = 64.2, ureaUnit = ConcentrationUnit.MG_DL)
        )

        val bisapDirect = BisapCalculator.calculate(inputsDirect)
        val bisapDerived = BisapCalculator.calculate(inputsDerived)

        assertEquals(bisapDirect.result.value, bisapDerived.result.value)
    }
}
