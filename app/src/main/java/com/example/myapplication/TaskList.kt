package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn {
        items(tasks.size) {
            TaskItem(tasks[it])
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Row {
        Text(task.name)
        Text(if (task.complete) "✓" else " ")
    }
}