package com.android.kotlinmvvmtodolist.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlinmvvmtodolist.data.TaskRepository
import com.android.kotlinmvvmtodolist.data.local.TaskEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository : TaskRepository
) : ViewModel(){

    val getAllTasks1 = repository.getAllTasks1()
    val getTasks1 = repository.getTasks1( )

    val getAllTasks = repository.getAllTasks()
    val getAllTasks2 = repository.getAllTasks2()

    val getAllPriorityTasks = repository.getAllPriorityTasks()

    fun insert(taskEntry: TaskEntry) = viewModelScope.launch {
        repository.insert(taskEntry)
    }

    fun delete(taskEntry: TaskEntry) = viewModelScope.launch{
            repository.deleteItem(taskEntry)
    }

    fun update(taskEntry: TaskEntry) = viewModelScope.launch{
        repository.updateData(taskEntry)
    }

    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }


    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>> {
        return repository.searchDatabase(searchQuery)
    }
    fun searchDatabaseList(searchQuery: String): LiveData<List<TaskEntry>> {
        return repository.searchDatabaseList(searchQuery)
    }
    fun searchDatabaseTask(searchQuery: String): LiveData<List<TaskEntry>> {
        return repository.searchDatabaseTask(searchQuery)
    }
   /* fun searchDate(dateSearch1: Date, dateSearch2: Date): LiveData<List<TaskEntry>>
    {    return repository.searchDate(dateSearch1,dateSearch2)
    }*/
     fun getTasksByTitle(title: String): LiveData<List<TaskEntry>> {
        return repository.getTasksByTitle(title)
      }

}