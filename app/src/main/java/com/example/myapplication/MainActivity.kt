package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var tasks by remember { mutableStateOf(mutableListOf<Task>())}
            Column {
                TaskEditor(Task(name = "", complete = false)) {
                    val newTasks = mutableListOf<Task>()
                    newTasks.add(it)
                    newTasks.addAll(tasks)
                    tasks = newTasks
                }
//                Text("Task name: ${task.name}")
                Column {
                    for (task in tasks) {
                        Text(task.name)
                    }
                }
            }
        }
    }
}

data class Task(var name: String, var complete: Boolean)

