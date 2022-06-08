package com.example.myapplication

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
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
            MyScaffold("Tasks!",
                fabIcon = Icons.Filled.Add,
                onFabClick = {
                    navController.navigate("tasks/add")
                }
            ) {
                TasksScreen(vmf = vmf) {
                    navController.navigate("tasks/${it.id}")
                }
            }
        }
        composable("tasks/add") {
            MyScaffold("Add Task") {
                AddTaskScreen(vmf) {
                    navController.navigate("tasks")
                }
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
                MyScaffold("Edit Task") {
                    EditTaskScreen(vmf, taskId = taskId) {
                        navController.navigate("tasks")
                    }
                }
            }
        }
    }
}

@Composable
fun MyScaffold(
    title: String,
    fabIcon: ImageVector? = null,
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(title)
            })
        },
        floatingActionButton = {
            fabIcon?.let {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(it, "Fab icon")
                }
            }
        }
    ) {
        content()
    }
}