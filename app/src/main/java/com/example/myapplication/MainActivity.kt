package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDao()
        setContent {
            val tasks by taskDao.getAll().observeAsState()
            Column {
                TaskEditor(Task(name = "", complete = false)) {
                    taskDao.insert(it)
                }
                tasks?.let {
                    TaskList(it)
                }
            }
        }
    }
}


