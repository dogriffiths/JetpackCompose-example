package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

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
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Buy milk!!!!",
            onValueChange = {},
        )
        Checkbox(
            checked = true,
            onCheckedChange = {},
        )
        Text("Complete?")
    }
}

//@Preview
//@Composable
//fun PreviewTaskEditor() {
//    TaskEditor()
//}