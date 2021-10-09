package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(vmf: MyViewModelFactory) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks = vm.getTasks().observeAsState()
    Column {
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