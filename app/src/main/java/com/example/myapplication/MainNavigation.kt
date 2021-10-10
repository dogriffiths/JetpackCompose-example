package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainNavigation(vmf: MyViewModelFactory) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "tasks") {
        composable("tasks") {
            MyScaffold(
                navController = navController,
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
            MyScaffold(
                navController = navController,
                title = "New task",
            ) {
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
                MyScaffold(
                    navController = navController,
                    title = "Edit task",
                ) {
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
    navController: NavController,
    title: String,
    fabIcon: ImageVector? = null,
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { MyAppBar(title, coroutineScope, scaffoldState) },
        floatingActionButton = {
            fabIcon?.let {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(it, "Fab icon")
                }
            }
        },
        drawerContent = {
            NavDrawer(navController = navController)
        },
        scaffoldState = scaffoldState,
    ) {
        content()
    }
}

@Composable
fun MyAppBar(
    title: String,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(title = { Text(title) }, navigationIcon = {
        Icon(
            imageVector = Icons.Default.Menu,
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            contentDescription = "Drawer icon"
        )
    })
}

@Preview
@Composable
fun PreviewMyAppBar() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()
    MyAppBar("App!", coroutineScope, scaffoldState)
}

@Composable
fun NavDrawer(navController: NavController) {
    Card(
        modifier = Modifier
        .clickable {
            navController.navigate("tasks")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Tasks", modifier = Modifier.padding(8.dp))
        }
    }
}