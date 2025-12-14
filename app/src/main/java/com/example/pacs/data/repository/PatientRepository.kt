package com.example.pacs.data.repository

import com.example.pacs.data.local.dao.PatientDao
import com.example.pacs.data.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

class PatientRepository(private val dao: PatientDao) {
    fun observe(): Flow<List<PatientEntity>> = dao.observePatients()
    suspend fun upsert(patient: PatientEntity): Long = dao.upsert(patient)
    suspend fun delete(patient: PatientEntity) = dao.delete(patient)
}
