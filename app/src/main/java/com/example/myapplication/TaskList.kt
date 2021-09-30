package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn {
        items(tasks.size) {
            TaskItem(tasks[it])
        }
    }
}

