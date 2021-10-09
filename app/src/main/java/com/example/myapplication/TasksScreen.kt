package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(vmf: MyViewModelFactory, onAddTask: () -> Unit) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks = vm.getTasks().observeAsState()
    Column {
        Button(
            onClick = onAddTask
        ) {
            Text("ADD TASK")
        }
        tasks.value?.let {
            TaskList(it)
        }
    }
}

@Composable
fun NewTaskScreen(vmf: MyViewModelFactory) {
    val vm: NewTaskScreenViewModel = viewModel(factory = vmf)
    Column {
        TaskEditor(Task(name = "", complete = false), onTaskChange = {
            vm.addTask(it)
        })
    }
}