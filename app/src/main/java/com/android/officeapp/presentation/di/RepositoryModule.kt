package com.android.kotlinmvvmtodolist.presentation.di

import com.android.kotlinmvvmtodolist.dataNotification.db.TaskCategoryDao
import com.android.kotlinmvvmtodolist.domain.TaskCategoryRepository
import com.android.kotlinmvvmtodolist.dataNotification.repository.TaskCategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskCategoryRepository(taskCategoryDao: TaskCategoryDao) : TaskCategoryRepository {
        return TaskCategoryRepositoryImpl(taskCategoryDao)
    }
}