package com.example.pacs.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pacs.data.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients ORDER BY alias")
    fun observePatients(): Flow<List<PatientEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(patient: PatientEntity): Long

    @Update
    suspend fun update(patient: PatientEntity)

    @Delete
    suspend fun delete(patient: PatientEntity)
}
