package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation(vmf: MyViewModelFactory) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "tasks"
    ) {
        composable("tasks") {
            TasksScreen(
                vmf = vmf,
                onAddTask = {
                    navController.navigate("tasks/add")
                }
            )
        }
        composable("tasks/add") {
            AddTaskScreen(vmf) {
                navController.navigate("tasks")
            }
        }
    }
}