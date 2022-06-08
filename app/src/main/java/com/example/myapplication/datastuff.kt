package com.example.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

//data class Task(var name: String, var complete: Boolean)
//class TaskDao {
//    private var _tasks: MutableLiveData<List<Task>> = MutableLiveData(mutableListOf<Task>())
//
//    fun insert(task: Task) {
//        val newTasks = mutableListOf<Task>()
//        newTasks.addAll(_tasks.value!!)
//        newTasks.add(task)
//        _tasks.postValue(newTasks)
//    }
//
//    fun getAll() = _tasks
//}

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "complete")
    var complete: Boolean = false,
)

@androidx.room.Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task WHERE id = :key")
    fun get(key: Long): LiveData<Task>

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): LiveData<List<Task>>
}

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}



