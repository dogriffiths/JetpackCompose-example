package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
            TextEditor()
        }
    }
}

@Composable
fun TextEditor() {
    Column {
        TextField(
            value = "Buy milk",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
        Checkbox(
            checked = true,
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
fun PreviewTextEditor() {
    TextEditor()
}