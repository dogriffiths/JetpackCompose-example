package com.example.myapplication

import androidx.lifecycle.MutableLiveData

class TaskDao {
    private var _tasks: MutableLiveData<List<Task>> =
        MutableLiveData(mutableListOf(Task(name = "Buy car", complete = true)))

    fun insert(task: Task) {
        val newTasks = mutableListOf<Task>()
        newTasks.add(task)
        _tasks.value?.let {
            newTasks.addAll(it)
        }
        _tasks.postValue(newTasks)
    }

    fun getAll() = _tasks
}

data class Task(var name: String, var complete: Boolean)