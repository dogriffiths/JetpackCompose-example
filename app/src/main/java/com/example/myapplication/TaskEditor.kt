package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun TaskEditor(task: Task, onTaskChange: (Task) -> Unit) {
    var name by rememberSaveable(task.name) { mutableStateOf(task.name) }
    var complete by rememberSaveable(task.complete) { mutableStateOf(task.complete) }
    Column {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Checkbox(
                checked = complete,
                onCheckedChange = {
                    complete = it
                }
            )
            Text("Complete?")
        }
        Button(
            onClick = {
                onTaskChange(task.copy(name = name, complete = complete))
                name = ""
                complete = false
            }
        ) {
            Text("SAVE")
        }
    }
}