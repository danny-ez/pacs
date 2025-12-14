package com.example.pacs.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pacs.data.local.dao.AssessmentDao
import com.example.pacs.data.local.dao.PatientDao
import com.example.pacs.data.local.entity.AssessmentEntity
import com.example.pacs.data.local.entity.PatientEntity

@Database(
    entities = [PatientEntity::class, AssessmentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun assessmentDao(): AssessmentDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pacs-db"
        ).fallbackToDestructiveMigration().build()
    }
}
