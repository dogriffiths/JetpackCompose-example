package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData

class TaskDao {
    private var _tasks: MutableLiveData<List<Task>> = MutableLiveData(mutableListOf(Task(name = "Buy car", complete = true)))

    fun insert(task: Task) {
        val newTasks = mutableListOf<Task>()
        newTasks.add(task)
        _tasks.value?.let {
            newTasks.addAll(it)
        }
        _tasks.postValue(newTasks)
    }

    fun getAll() = _tasks
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
        setContent {
            val tasks = taskDao.getAll().observeAsState()
            Column {
                TaskEditor(Task(name = "", complete = false), onTaskChange = {
                    taskDao.insert(it)
                })
                tasks.value?.let {
                    TaskList(it)
                }
            }
        }
    }
}

data class Task(var name: String, var complete: Boolean)


