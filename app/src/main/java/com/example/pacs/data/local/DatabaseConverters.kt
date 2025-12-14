package com.example.pacs.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Instant

class DatabaseConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromInstant(value: Instant?): Long? = value?.toEpochMilli()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? = list?.let { json.encodeToString(it) }

    @TypeConverter
    fun toStringList(value: String?): List<String>? = value?.let { json.decodeFromString(it) }
}
