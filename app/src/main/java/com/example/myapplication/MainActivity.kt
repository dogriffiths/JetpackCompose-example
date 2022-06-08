package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
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