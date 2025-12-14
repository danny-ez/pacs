package com.example.pacs.data.repository

import com.example.pacs.data.local.dao.AssessmentDao
import com.example.pacs.data.local.entity.AssessmentEntity
import kotlinx.coroutines.flow.Flow

class AssessmentRepository(private val dao: AssessmentDao) {
    fun observe(patientId: Long): Flow<List<AssessmentEntity>> = dao.observeForPatient(patientId)
    suspend fun save(assessment: AssessmentEntity): Long = dao.insert(assessment)
}
