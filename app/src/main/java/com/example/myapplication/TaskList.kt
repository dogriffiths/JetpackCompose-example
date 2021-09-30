package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskList(tasks: List<Task>) {
    Column {
        for (task in tasks) {
            Text(task.name)
        }
    }
}