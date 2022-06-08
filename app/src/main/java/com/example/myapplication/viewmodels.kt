package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksScreenViewModel(val taskDao: TaskDao): ViewModel() {
    fun getTasks() = taskDao.getAll()
}

class AddTaskScreenViewModel(val taskDao: TaskDao): ViewModel() {
    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }
}

class EditTaskScreenViewModel(val taskDao: TaskDao): ViewModel() {
    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }
}

class MyViewModelFactory(val taskDao: TaskDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksScreenViewModel::class.java)) {
            return TasksScreenViewModel(taskDao) as T
        }
        if (modelClass.isAssignableFrom(AddTaskScreenViewModel::class.java)) {
            return AddTaskScreenViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Cannot find a viewmodel you dope...")
    }
}