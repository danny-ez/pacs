package com.example.pacs.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BunResolution(
    val bunMgDl: Double? = null,
    val provenance: BunProvenance = BunProvenance.MISSING,
    val sourceValue: Double? = null,
    val sourceUnit: ConcentrationUnit? = null
)

@Serializable
data class DerivedValues(
    val bun: BunResolution = BunResolution()
)
