package com.example.todoappincompose

import android.R.attr.onClick
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoappincompose.ui.theme.TodoAppInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppInComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context=LocalContext.current

    //TODO MIght need to change this later
    var TaskList = mutableListOf<Task>();


    val padding = 25.dp
    Column(
        Modifier.padding(padding)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        var text = remember { mutableStateOf("") }
Row(){
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Enter your Task") },
        placeholder = { Text("Task ...") },
        singleLine = true,
        //modifier = Modifier
            //.fillMaxWidth()

    )


    Spacer(modifier = Modifier.width(20.dp))
    Button(onClick = {
        //when the button clicks
        //this will run in here


        Toast.makeText(context, "${text.value}", Toast.LENGTH_SHORT).show()

        TaskList.add(Task(TaskList.size,false,text.value))

        Log.d("TaskApp", "Starting printing TaskList")
        Log.d("TaskApp", TaskList.toString())

    }) {
        Text("Filled")
    }

}




    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppInComposeTheme {
        Greeting("Android")
    }
}