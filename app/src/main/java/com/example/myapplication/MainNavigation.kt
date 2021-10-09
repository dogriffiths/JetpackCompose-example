package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainNavigation(vmf: MyViewModelFactory) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "tasks") {
        composable("tasks") {
            TasksScreen(vmf, onTaskClicked = {
                navController.navigate("tasks/${it.id}")
            }) {
                navController.navigate("tasks/new")
            }
        }
        composable("tasks/new") {
            NewTaskScreen(vmf) {
                navController.navigate("tasks")
            }
        }
        composable(
            route = "tasks/{taskId}",
            arguments = listOf(
                navArgument ("taskId") {
                    type = NavType.LongType
                },
            )
        ) {
            it.arguments?.let {
                val taskId = it.get("taskId") as Long
                EditTaskScreen(vmf, taskId) {
                    navController.navigate("tasks")
                }
            }
        }
    }
}