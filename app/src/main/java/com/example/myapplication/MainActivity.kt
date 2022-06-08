package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TasksScreenViewModel(val taskDao: TaskDao): ViewModel() {
    fun addTask(task: Task) {
        taskDao.insert(task)
    }

    fun getTasks() = taskDao.getAll()
}

class MyViewModelFactory(val taskDao: TaskDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksScreenViewModel::class.java)) {
            return TasksScreenViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Cannot find a viewmodel you dope...")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
        val vmf = MyViewModelFactory()
        setContent {
            val tasks by taskDao.getAll().observeAsState()
            Column {
                TaskEditor(
                    task = Task(name = "", complete = false),
                    onTaskChange = {
                        taskDao.insert(it)
                    }
                )
                tasks?.let {
                    TaskList(it)
                }
            }
        }
    }

}

//@Preview
//@Composable
//fun PreviewTaskEditor() {
//    TaskEditor()
//}