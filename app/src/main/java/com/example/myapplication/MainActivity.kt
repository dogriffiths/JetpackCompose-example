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
    var name = "Buy milk!!!!!"
    Column {
        TextField(
            value = name,
            onValueChange = {
                            name = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Checkbox(checked = true, onCheckedChange = {})
            Text("Complete?")
        }
    }
}
