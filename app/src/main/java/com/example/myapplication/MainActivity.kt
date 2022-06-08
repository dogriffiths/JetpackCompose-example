package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class Task(var name: String, var complete: Boolean)
class TaskDao {
    private var _tasks: List<Task> = mutableListOf(Task(name = "Buy milk", complete = true))

    fun insert(task: Task) {
        val newTasks = mutableListOf<Task>()
        newTasks.add(task)
        newTasks.addAll(_tasks)
        _tasks = newTasks
    }
}

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

//@Preview
//@Composable
//fun PreviewTaskEditor() {
//    TaskEditor()
//}