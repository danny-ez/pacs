package com.example.pacs.domain.model

data class ScoreResult<T>(
    val value: T?,
    val status: ScoreStatus,
    val missingFields: List<String> = emptyList(),
    val warnings: List<String> = emptyList(),
    val interpretation: String = ""
)
