package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskEditor(Task(name = "Buy milk", complete = true))
        }
    }
}

data class Task(var name: String, var complete: Boolean)

@Composable
fun TaskEditor(task: Task) {
    var name by rememberSaveable(task.name) { mutableStateOf(task.name) }
    var complete by rememberSaveable(task.complete) { mutableStateOf(task.complete) }
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = complete,
                onCheckedChange = {
                    complete = it
                },
            )
            Text("Complete?")
        }
        Button(
            onClick = {},
        ) {
            Text("Save")
        }
    }
}

//@Preview
//@Composable
//fun PreviewTaskEditor() {
//    TaskEditor()
//}