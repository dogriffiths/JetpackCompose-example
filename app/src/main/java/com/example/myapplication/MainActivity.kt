package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDatabase.getInstance(applicationContext).taskDao
        val vmf = MyViewModelFactory(taskDao)
        setContent {
            TasksScreen(vmf)
        }
    }
}

