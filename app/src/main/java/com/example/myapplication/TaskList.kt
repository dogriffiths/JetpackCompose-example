package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn {
        items(tasks.size) {
            val task = tasks[it]
            Row {
                Text(task.name)
                Text(if (task.complete) "  ✓" else "")
            }
        }
    }
}