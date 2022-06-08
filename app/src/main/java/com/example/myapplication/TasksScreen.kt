package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(vmf: MyViewModelFactory) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks by vm.getTasks().observeAsState()
    Column {
        Button(
            onClick = {}
        ) {
            Text("ADD TASK")
        }
        tasks?.let {
            TaskList(it)
        }
    }
}

@Composable
fun AddTaskScreen(vmf: MyViewModelFactory) {
    val vm: AddTaskScreenViewModel = viewModel(factory = vmf)
    TaskEditor(
        task = Task(name = "", complete = false),
        onTaskChange = {
            vm.addTask(it)
        }
    )
}

