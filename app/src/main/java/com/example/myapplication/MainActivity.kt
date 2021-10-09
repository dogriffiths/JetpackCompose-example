package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskEditor()
        }
    }
}


@Composable
fun TaskEditor() {
    var name by rememberSaveable { mutableStateOf("Buy milk!!!!!") }
    var complete by rememberSaveable { mutableStateOf(true) }
    Column {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Checkbox(checked = complete, onCheckedChange = {complete = it})
            Text("Complete?")
        }
    }
}
