package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(vmf: MyViewModelFactory, onTaskClicked: (Task) -> Unit, onAddTask: () -> Unit) {
    val vm: TasksScreenViewModel = viewModel(factory = vmf)
    val tasks = vm.getTasks().observeAsState()
    MyScaffold(
        title = "Tasks!",
        fabIcon = Icons.Filled.Add,
        onFabClick = onAddTask
    ) {
        tasks.value?.let {
            TaskList(it) {
                onTaskClicked(it)
            }
        }
    }
}

@Composable
fun NewTaskScreen(vmf: MyViewModelFactory, onTaskAdded: () -> Unit) {
    val vm: NewTaskScreenViewModel = viewModel(factory = vmf)
    MyScaffold(title = "New task") {
        Column {
            TaskEditor(Task(name = "", complete = false), onTaskChange = {
                vm.addTask(it)
                onTaskAdded()
            })
        }
    }
}


@Composable
fun EditTaskScreen(vmf: MyViewModelFactory, taskId: Long, onTaskAdded: () -> Unit) {
    val vm: EditTaskScreenViewModel = viewModel(factory = vmf)
    val task = vm.getTask(taskId).observeAsState()
    MyScaffold("Edit task") {
        Column {
            task.value?.let {
                TaskEditor(it, onTaskChange = {
                    vm.saveTask(it)
                    onTaskAdded()
                })
            }
        }
    }
}

@Composable
fun MyScaffold(
    title: String,
    fabIcon: ImageVector? = null,
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) },
        floatingActionButton = {
            fabIcon?.let {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(it, "Fab icon")
                }
            }
        }
    ) {
        content()
    }
}