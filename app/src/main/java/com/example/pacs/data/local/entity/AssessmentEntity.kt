package com.example.pacs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.Instant

@Entity(tableName = "assessments")
@Serializable
data class AssessmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val patientId: Long,
    val datetime: Instant = Instant.now(),
    val inputsSerialized: String,
    val derivedValuesSerialized: String,
    val resultsSerialized: String
)
