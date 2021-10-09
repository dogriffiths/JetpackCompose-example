package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var name = remember { mutableStateOf("Buy milk!!!!!")}
    Column {
        TextField(
            value = name.value,
            onValueChange = {
                            name.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Checkbox(checked = true, onCheckedChange = {})
            Text("Complete?")
        }
    }
}
