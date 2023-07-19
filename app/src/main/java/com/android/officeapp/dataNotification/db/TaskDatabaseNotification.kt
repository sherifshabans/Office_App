package com.android.kotlinmvvmtodolist.dataNotification.db

import androidx.room.*
import com.android.kotlinmvvmtodolist.dataNotification.model.CategoryInfo
import com.android.kotlinmvvmtodolist.dataNotification.model.TaskInfo

@Database(entities = [TaskInfo::class, CategoryInfo::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class TaskDatabaseNotification : RoomDatabase() {
    abstract fun getTaskCategoryDao() : TaskCategoryDao
}