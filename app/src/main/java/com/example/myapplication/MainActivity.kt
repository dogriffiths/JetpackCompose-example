package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDatabase.getInstance(this).taskDao
        val vmf = MyViewModelFactory(taskDao)
        setContent {
            MyApplicationTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    MainNavigation(vmf)
                }
            }
        }
    }

}

