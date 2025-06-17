package com.example.todoappincompose

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

    //make a list that is reactive so when it changes
        //it will triger a recompistion
            //for example deliting or adding to it
    var TaskList by remember {mutableStateOf(emptyList<Task>())}







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

        //ad to the tasklist that is an observable state
        TaskList=TaskList+Task(TaskList.size,false,text.value)

        //TaskList.add(Task(TaskList.size,false,text.value))

        Log.d("TaskApp", "Starting printing TaskList")
        Log.d("TaskApp", TaskList.toString())

    }) {
        Text("Filled")
    }

}

        LazyColumn {
items(TaskList) {
    task -> TaskCard(task,onDelteTask = {TaskList = TaskList.filter { it.id != task.id}})

}

}

        }







    }

@Composable
fun TaskCard(task: Task, onDelteTask: () -> Unit) {


    //this variable will have an remmerby by

    var isToggled by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    //this will be the checkbox or empty checkbox icon
Row() {
    //we need a IconButton that is wrapepd outside the button so it clicks
    IconButton(onClick={

        //set it opposite to what it was
        isToggled= !isToggled




    }) {
    Icon(

        painter =if(isToggled) {painterResource(id = R.drawable.outline_check_box_24_white)}
        else {painterResource(id = R.drawable.outline_check_box_outline_blank_24)},
        contentDescription = "checkbox"
    )}
    Text(task.title)
    Spacer(modifier = Modifier.width(20.dp))

    //lets put in the thrashcan symbol here
    IconButton(onClick= {

        //create a toast to make sure the code is runnign
        Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show()

        //delete the current task from the list
                onDelteTask()
                        }
    ){


    Icon(
        painter = painterResource(id = R.drawable.outline_cancel_24),
        contentDescription = "trashcan"
    )}

}

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppInComposeTheme {
        Greeting("Android")
    }
}