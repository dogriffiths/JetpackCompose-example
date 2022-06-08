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
        composable(
            "tasks/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.LongType
                }
            )
        ) {
            it.arguments?.let {
                val taskId = it.get("taskId") as Long
                EditTaskScreen(vmf, taskId = 17L) {
                    navController.navigate("tasks")
                }
            }
        }
    }
}