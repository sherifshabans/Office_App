package com.android.kotlinmvvmtodolist.data


import androidx.lifecycle.LiveData
import com.android.kotlinmvvmtodolist.data.local.TaskDao
import com.android.kotlinmvvmtodolist.data.local.TaskEntry
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    suspend fun insert(taskEntry: TaskEntry) = taskDao.insert(taskEntry)

    suspend fun updateData(taskEntry: TaskEntry) = taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry) = taskDao.delete(taskEntry)

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }


    fun getTasks1() = taskDao.getTasks1()
    fun getAllTasks() = taskDao.getAllTasks()


    fun getAllTasks1() = taskDao.getAllTasks1()
    fun getAllTasks2() = taskDao.getAllTasks2()


    fun getAllPriorityTasks() = taskDao.getAllPriorityTasks()

    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>> {
        return taskDao.searchDatabase(searchQuery)
    }

    fun searchDatabaseTask(searchQuery: String): LiveData<List<TaskEntry>> {
        return taskDao.searchDatabaseforTask(searchQuery)
    }
    fun searchDatabaseList(searchQuery: String): LiveData<List<TaskEntry>> {
        return taskDao.searchDatabaseforList(searchQuery)
    }

   /* fun searchDate(dateSearch1: Date, dateSearch2: Date): LiveData<List<TaskEntry>>
    {    return taskDao.searchDate(dateSearch1,dateSearch2)
    }*/
    fun getTasksByTitle(title: String): LiveData<List<TaskEntry>> {
        return taskDao.getTasksByTitle(title)
    }
}
