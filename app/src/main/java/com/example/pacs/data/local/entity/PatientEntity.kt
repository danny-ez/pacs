package com.example.pacs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.Instant

@Entity(tableName = "patients")
@Serializable
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val alias: String,
    val sex: String? = null,
    val birthDateMillis: Long? = null,
    val notes: String? = null,
    val createdAt: Instant = Instant.now()
)
