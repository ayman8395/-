package com.guidance.app.di

import android.content.Context
import androidx.room.Room
import com.guidance.app.data.local.AppDatabase

object DatabaseModule {
    
    private var database: AppDatabase? = null
    
    fun provideEncryptedDatabase(context: Context): AppDatabase {
        if (database == null) {
            database = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "guidance_db"
            ).build()
        }
        return database!!
    }
}