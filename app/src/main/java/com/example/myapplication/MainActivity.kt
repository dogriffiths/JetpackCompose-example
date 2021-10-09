package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var tasks by remember { mutableStateOf(mutableListOf<Task>())}
            Column {
                TaskEditor(Task(name = "", complete = false), onTaskChange = {
                    val newTasks = mutableListOf<Task>()
                    newTasks.add(it)
                    newTasks.addAll(tasks)
                    tasks = newTasks
                })
                TaskList(tasks)
            }
        }
    }
}

data class Task(var name: String, var complete: Boolean)


