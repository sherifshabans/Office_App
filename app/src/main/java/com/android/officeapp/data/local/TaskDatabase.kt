package com.android.kotlinmvvmtodolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.kotlinmvvmtodolist.dataNotification.db.DateConverter

@Database(entities = [TaskEntry::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}