package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskDao = TaskDatabase.getInstance(this).taskDao
        val vmf = MyViewModelFactory(taskDao)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "tasks") {
                composable("tasks") {
                    TasksScreen(vmf) {
                        navController.navigate("tasks/new")
                    }
                }
                composable("tasks/new") {
                    NewTaskScreen(vmf)
                }
            }

        }
    }
}


