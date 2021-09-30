package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(vmf: MyViewModelFactory, onTaskClicked: (Task) -> Unit, onAddTask: () -> Unit) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks by vm.getTasks().observeAsState()
    MyScaffold("Tasks!") {
        Column {
            Button(
                onClick = onAddTask
            ) {
                Text("ADD TASK")
            }
            tasks?.let {
                TaskList(it) {
                    onTaskClicked(it)
                }
            }
        }
    }
}


@Composable
fun NewTaskScreen(vmf: MyViewModelFactory, onTaskAdded: () -> Unit) {
    val vm: NewTaskScreenViewModel = viewModel(factory = vmf)
    MyScaffold("New task") {
        Column {
            TaskEditor(Task(name = "", complete = false)) {
                vm.addTask(it)
                onTaskAdded()
            }
        }
    }
}


@Composable
fun EditTaskScreen(vmf: MyViewModelFactory, taskId: Long, onTaskSaved: () -> Unit) {
    val vm: EditTaskScreenViewModel = viewModel(factory = vmf)
    val task by vm.getTask(taskId).observeAsState()
    MyScaffold("Edit task") {
        Column {
            task?.let {
                TaskEditor(it) {
                    vm.saveTask(it)
                    onTaskSaved()
                }
            }
        }
    }
}

@Composable
fun MyScaffold(title: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {TopAppBar(title = {Text(title)})}
    ) {
        content()
    }
}