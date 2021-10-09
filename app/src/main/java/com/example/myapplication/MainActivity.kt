package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import java.lang.IllegalArgumentException

class TasksScreenViewModel(val taskDao: TaskDao): ViewModel() {
    fun addTask(task: Task) {
        taskDao.insert(task)
    }

    fun getTasks() = taskDao.getAll()
}

class MyViewModelFactory(val taskDao: TaskDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksScreenViewModel::class.java)) {
            return TasksScreenViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Never heard of that kind of view model")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
        val vmf = MyViewModelFactory(taskDao)
        setContent {
            val vm: TasksScreenViewModel = viewModel(factory = vmf)
            val tasks = vm.getTasks().observeAsState()
            Column {
                TaskEditor(Task(name = "", complete = false), onTaskChange = {
                    vm.addTask(it)
                })
                tasks.value?.let {
                    TaskList(it)
                }
            }
        }
    }
}


