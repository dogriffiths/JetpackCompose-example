package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextEditor(Task(name = "Buy bread", complete = true))
        }
    }
}

data class Task(var name: String, var complete: Boolean)

@Composable
fun TextEditor(task: Task) {
    var name by rememberSaveable { mutableStateOf(task.name) }
    var complete by rememberSaveable { mutableStateOf(task.complete) }
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
    }
}

@Preview
@Composable
fun PreviewTextEditor() {
    TextEditor(Task(name = "Buy bread", complete = true))
}