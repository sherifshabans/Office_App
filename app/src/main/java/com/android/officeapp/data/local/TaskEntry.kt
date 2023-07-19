package com.android.kotlinmvvmtodolist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.kotlinmvvmtodolist.util.Constants.TASK_TABLE
import java.io.Serializable
import java.util.*



@Entity(tableName = TASK_TABLE)
data class TaskEntry(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date :Date,
    var typeOfStation :Int,
    var nameOfStation :Int,
    var numberOfCircle :String,
    var numberOfCase :String,
    var description :String,
    var subject :String,
    var vsname: String,
    var note: String,
    var priority: Int,
    var numberOfdif:String,
    var center :String,
    var name :String,
    var location :String,
    var timestamp: Long
): Serializable
