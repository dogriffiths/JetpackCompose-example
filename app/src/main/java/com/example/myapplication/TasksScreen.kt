package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(vmf: MyViewModelFactory, onAddTask: () -> Unit) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks by vm.getTasks().observeAsState()
    Column {
        Button(
            onClick = onAddTask
        ) {
            Text("ADD TASK")
        }
        tasks?.let {
            TaskList(it)
        }
    }
}

@Composable
fun AddTaskScreen(vmf: MyViewModelFactory, onTaskSaved: () -> Unit) {
    val vm: AddTaskScreenViewModel = viewModel(factory = vmf)
    TaskEditor(
        task = Task(name = "", complete = false),
        onTaskChange = {
            vm.addTask(it)
            onTaskSaved()
        }
    )
}

@Composable
fun EditTaskScreen(vmf: MyViewModelFactory, taskId: Long, onTaskSaved: () -> Unit) {
    val vm: EditTaskScreenViewModel = viewModel(factory = vmf)
    val task by vm.getTask(taskId).observeAsState()
    task?.let {
        TaskEditor(
            task = it,
            onTaskChange = {
                vm.updateTask(it)
                onTaskSaved()
            }
        )
    }
}

