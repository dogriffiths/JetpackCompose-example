package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
        val vmf = MyViewModelFactory(taskDao)
        setContent {
            TasksScreen(vmf)
        }
    }
}

@Composable
fun TasksScreen(vmf: MyViewModelFactory) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks by vm.getTasks().observeAsState()
    Column {
        TaskEditor(
            task = Task(name = "", complete = false),
            onTaskChange = {
                vm.addTask(it)
            }
        )
        tasks?.let {
            TaskList(it)
        }
    }
}


//@Preview
//@Composable
//fun PreviewTaskEditor() {
//    TaskEditor()
//}