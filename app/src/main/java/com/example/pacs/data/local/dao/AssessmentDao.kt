package com.example.pacs.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pacs.data.local.entity.AssessmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssessmentDao {
    @Query("SELECT * FROM assessments WHERE patientId = :patientId ORDER BY datetime DESC")
    fun observeForPatient(patientId: Long): Flow<List<AssessmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(assessment: AssessmentEntity): Long
}
