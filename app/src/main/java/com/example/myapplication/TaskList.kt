package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskList(tasks: List<Task>) {
    Column {
        for (task in tasks) {
            Row {
                Text(task.name)
                Text(if (task.complete) "  ✓" else "")
            }
        }
    }
}