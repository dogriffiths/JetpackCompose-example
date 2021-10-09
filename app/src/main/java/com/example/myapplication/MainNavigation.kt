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
    NavHost(navController = navController, startDestination = "tasks") {
        composable("tasks") {
            MyScaffold(
                title = "Tasks!",
                fabIcon = Icons.Filled.Add,
                onFabClick = {
                    navController.navigate("tasks/new")
                }
            ) {
                TasksScreen(vmf) {
                    navController.navigate("tasks/${it.id}")
                }
            }
        }
        composable("tasks/new") {
            MyScaffold(title = "New task") {
                NewTaskScreen(vmf) {
                    navController.navigate("tasks")
                }
            }
        }
        composable(
            route = "tasks/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.LongType
                },
            )
        ) {
            it.arguments?.let {
                val taskId = it.get("taskId") as Long
                MyScaffold(title = "Edit task") {
                    EditTaskScreen(vmf, taskId) {
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
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) },
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