package com.guidance.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guidance.app.data.local.dao.GuidanceDao
import com.guidance.app.data.local.entity.CaseEntity
import com.guidance.app.data.local.entity.CaseStudyFormEntity

@Database(
    entities = [CaseEntity::class, CaseStudyFormEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun caseDao(): GuidanceDao
}