package com.android.kotlinmvvmtodolist.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(taskEntry: TaskEntry)

    @Delete
    suspend fun delete(taskEntry: TaskEntry)

    @Update
    suspend fun update(taskEntry: TaskEntry)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM task_table  GROUP BY numberOfCase   ORDER BY timestamp DESC    ")
    fun getAllTasks(): Flow<List<TaskEntry>>

/*
    @Query("SELECT * FROM task_table WHERE priority=0  AND numberOfCase IN (SELECT numberOfCase FROM task_table GROUP BY numberOfCase )  ORDER BY timestamp DESC")
    fun getTasks1(): Flow<List<TaskEntry>>*/

    @Query("SELECT * FROM task_table WHERE priority ==0  AND  numberOfCase IN (SELECT numberOfCase FROM task_table GROUP BY numberOfCase )  ORDER BY timestamp DESC")
    fun getTasks1(): Flow<List<TaskEntry>>

    @Query("SELECT * FROM task_table WHERE priority ==1  AND numberOfCase IN" +
     " (SELECT numberOfCase FROM task_table GROUP BY numberOfCase )  ORDER BY timestamp DESC")
    fun getTasks2(): Flow<List<TaskEntry>>


    @Query("SELECT *FROM task_table WHERE priority ==1  GROUP BY numberOfCase  ORDER BY timestamp DESC")
    fun getAllTasks2(): Flow<List<TaskEntry>>
   @Query("SELECT *FROM task_table  WHERE priority ==0  GROUP BY numberOfCase  ORDER BY timestamp DESC")
    fun getAllTasks1(): Flow<List<TaskEntry>>

    @Query("SELECT * FROM task_table ORDER BY timestamp DESC")
    fun getAllPriorityTasks(): Flow<List<TaskEntry>>

    @Query("SELECT * FROM task_table ORDER BY timestamp DESC")
    fun getAllPriorityTasksList(): Flow<List<TaskEntry>>

    @Query("SELECT * FROM task_table       ORDER BY timestamp DESC")
    fun getAllPriorityTasksTask(): Flow<List<TaskEntry>>

    
    @Query("SELECT * FROM task_table WHERE  nameOfStation LIKE :searchQuery " +
            " OR nameOfStation LIKE :searchQuery"+
            " OR typeOfStation LIKE :searchQuery"+
            " OR numberOfCircle LIKE :searchQuery " +
            "OR subject LIKE :searchQuery " +
            "OR numberOfCase  LIKE :searchQuery " +
            "OR description  LIKE :searchQuery " +
            "OR numberOfdif  LIKE :searchQuery " +
            "OR center  LIKE :searchQuery " +
            "OR vsname  LIKE :searchQuery " +
            "OR priority  LIKE :searchQuery " +
            "OR note  LIKE :searchQuery " +
            "OR name  LIKE :searchQuery " +
            "GROUP BY numberOfCase " +
            " ORDER BY timestamp DESC  ")
    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>>


    @Query("SELECT * FROM task_table  WHERE priority ==1   " +
            " AND  nameOfStation LIKE :searchQuery"+
            "  OR typeOfStation LIKE :searchQuery"+
            " OR numberOfCircle LIKE :searchQuery " +
            "OR subject LIKE :searchQuery " +
            "OR numberOfCase  LIKE :searchQuery " +
            "OR description  LIKE :searchQuery " +
            "OR vsname  LIKE :searchQuery " +
            "OR timestamp  LIKE :searchQuery " +
            "OR priority  LIKE :searchQuery " +
            " OR note  LIKE :searchQuery " +
            " GROUP BY numberOfCase" +
            " ORDER BY timestamp DESC  ")

        fun searchDatabaseforList(searchQuery: String): LiveData<List<TaskEntry>>
    @Query("SELECT * FROM task_table WHERE priority ==0  " +
            " AND nameOfStation LIKE :searchQuery"+
            "  OR typeOfStation LIKE :searchQuery"+
            " OR numberOfCircle LIKE :searchQuery " +
            "OR subject LIKE :searchQuery " +
            "OR numberOfCase  LIKE :searchQuery " +
            "OR description  LIKE :searchQuery " +
            "OR vsname  LIKE :searchQuery " +
            "OR priority  LIKE :searchQuery " +
            "OR timestamp  LIKE :searchQuery " +
            " OR note  LIKE :searchQuery " +
            " GROUP BY numberOfCase" +
            " ORDER BY timestamp DESC  ")
    fun searchDatabaseforTask(searchQuery: String): LiveData<List<TaskEntry>>


  /*  @Query("SELECT * FROM task_table WHERE  priority == 1 AND date <= :dateSearch1 AND date1>= :dateSearch2 "+
            " ORDER BY timestamp DESC  "
    )
    fun searchDate(dateSearch1:Date,dateSearch2:Date): LiveData<List<TaskEntry>>
*/
    /*
    @Query("SELECT * " +
            "FROM task_table " +
            "WHERE priority = 0 " +
            "AND date > :currentTime")
    fun getActiveAlarms(currentTime : Date) : List<TaskEntry>
*/
@Query("SELECT * FROM task_table WHERE   numberOfCase LIKE :numberOfCase ORDER BY timestamp DESC")
fun getTasksByTitle(numberOfCase: String): LiveData<List<TaskEntry>>





}