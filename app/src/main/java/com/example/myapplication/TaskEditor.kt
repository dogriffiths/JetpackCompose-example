package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskEditor(task: Task, onTaskChanged: (Task) -> Unit) {
    var name by rememberSaveable(task.name) { mutableStateOf(task.name) }
    var complete by rememberSaveable(task.complete) { mutableStateOf(task.complete) }
    Column {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier.fillMaxWidth(),
        )
        Row {
            Checkbox(
                checked = complete,
                onCheckedChange = {
                    complete = it
                },
            )
            Text("Complete?")
        }
        Button(onClick = {
            onTaskChanged(task.copy(name = name, complete = complete))
            name = ""
            complete = false
        }) {
            Text("SAVE")
        }
    }
}

@Preview
@Composable
fun PreviewTaskEditor() {
    TaskEditor(Task(name = "Buy bread", complete = true)) {

    }
}