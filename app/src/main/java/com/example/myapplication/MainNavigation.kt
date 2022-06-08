package com.example.myapplication

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
                onTaskClicked = {
                    navController.navigate("tasks/${it.id}")
                },
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
                EditTaskScreen(vmf, taskId = taskId) {
                    navController.navigate("tasks")
                }
            }
        }
    }
}

@Composable
fun MyScaffold(
    title: String,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(title)
            })
        }
    ) {
        content()
    }
}