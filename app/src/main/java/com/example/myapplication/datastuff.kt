package com.example.myapplication

import androidx.lifecycle.MutableLiveData

class TaskDao {
    private var _tasks: MutableLiveData<List<Task>> =
        MutableLiveData(mutableListOf(Task(name = "Buy fish", complete = true)))

    fun insert(task: Task) {
        val newTasks = mutableListOf<Task>()
        newTasks.add(task)
        newTasks.addAll(_tasks.value!!)
        _tasks.postValue(newTasks)
    }

    fun getAll() = _tasks
}

data class Task(var name: String, var complete: Boolean)