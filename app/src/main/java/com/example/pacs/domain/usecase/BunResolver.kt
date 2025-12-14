package com.example.pacs.domain.usecase

import com.example.pacs.domain.model.BunProvenance
import com.example.pacs.domain.model.BunResolution
import com.example.pacs.domain.model.ConcentrationUnit
import com.example.pacs.domain.model.LabInput

object BunResolver {
    fun resolve(labInput: LabInput): BunResolution {
        labInput.bun?.let { bunValue ->
            val mgDl = when (labInput.bunUnit ?: ConcentrationUnit.MG_DL) {
                ConcentrationUnit.MG_DL -> bunValue
                ConcentrationUnit.MMOL_L -> bunValue * 2.8
            }
            return BunResolution(
                bunMgDl = mgDl,
                provenance = BunProvenance.DIRECT_BUN,
                sourceValue = bunValue,
                sourceUnit = labInput.bunUnit ?: ConcentrationUnit.MG_DL
            )
        }

        labInput.urea?.let { ureaValue ->
            val unit = labInput.ureaUnit ?: ConcentrationUnit.MG_DL
            val bunMgDl = when (unit) {
                ConcentrationUnit.MG_DL -> ureaValue / 2.14
                ConcentrationUnit.MMOL_L -> ureaValue * 2.8
            }
            return BunResolution(
                bunMgDl = bunMgDl,
                provenance = BunProvenance.DERIVED_FROM_UREA,
                sourceValue = ureaValue,
                sourceUnit = unit
            )
        }

        return BunResolution(provenance = BunProvenance.MISSING)
    }
}
