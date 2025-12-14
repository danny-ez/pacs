package com.example.pacs.domain.model

enum class ConcentrationUnit { MG_DL, MMOL_L }

enum class BunProvenance {
    DIRECT_BUN,
    DERIVED_FROM_UREA,
    MISSING
}

enum class ScoreStatus { COMPLETE, PARTIAL, MISSING }
