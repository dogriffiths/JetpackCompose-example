package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun TaskList(tasks: List<Task>, onTaskClicked: (Task) -> Unit) {
    LazyColumn {
        items(tasks.size) {
            val task = tasks[it]
            TaskItem(task) {
                onTaskClicked(task)
            }
        }
    }
}

