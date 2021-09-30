package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*

class TaskDao {
    private var _tasks: List<Task> = mutableListOf(Task(name = "Buy fish", complete = true))

    fun insert(task: Task) {
        val newTasks = mutableListOf<Task>()
        newTasks.add(task)
        newTasks.addAll(_tasks)
        _tasks = newTasks
    }

    fun getAll() = _tasks
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
        setContent {
            var tasks = taskDao.getAll()
            Column {
                TaskEditor(Task(name = "", complete = false)) {
                    taskDao.insert(it)
                }
                TaskList(tasks)
            }
        }
    }
}


data class Task(var name: String, var complete: Boolean)

