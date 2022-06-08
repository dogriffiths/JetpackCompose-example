package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var tasks by remember { mutableStateOf(mutableListOf<Task>())}
            Column {
                TaskEditor(
                    task = Task(name = "", complete = false),
                    onTaskChange = {
                        val newTasks = mutableListOf<Task>()
                        newTasks.add(it)
                        newTasks.addAll(tasks)
                        tasks = newTasks
                    }
                )
                TaskList(tasks)
            }
        }
    }

}

data class Task(var name: String, var complete: Boolean)

@Composable
fun TaskEditor(task: Task, onTaskChange: (Task) -> Unit) {
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
            onClick = {
                onTaskChange(task.copy(name = name, complete = complete))
                name = ""
                complete = false
            },
        ) {
            Text("SAVE")
        }
    }
}

//@Preview
//@Composable
//fun PreviewTaskEditor() {
//    TaskEditor()
//}