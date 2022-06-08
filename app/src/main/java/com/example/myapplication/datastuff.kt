package com.example.myapplication

import androidx.lifecycle.MutableLiveData

data class Task(var name: String, var complete: Boolean)
class TaskDao {
    private var _tasks: MutableLiveData<List<Task>> = MutableLiveData(
        mutableListOf(Task(name = "Buy milk", complete = true))
    )

    fun insert(task: Task) {
        val newTasks = mutableListOf<Task>()
        newTasks.add(task)
        newTasks.addAll(_tasks.value!!)
//        _tasks = newTasks
        _tasks.postValue(newTasks)
    }

    fun getAll() = _tasks
}