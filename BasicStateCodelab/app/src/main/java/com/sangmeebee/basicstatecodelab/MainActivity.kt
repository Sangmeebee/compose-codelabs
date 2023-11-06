package com.sangmeebee.basicstatecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sangmeebee.basicstatecodelab.ui.theme.BasicStateCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicStateCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { WellnessTask.getWellnessTasks() }
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            WellnessTaskItem(taskName = item.label)
        }
    }
}

@Composable
fun WellnessTaskItem(modifier: Modifier = Modifier, taskName: String) {
    var checkedState by rememberSaveable { mutableStateOf(false) }
    WellnessTaskItem(
        modifier = modifier,
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { checked ->
            checkedState = checked
        },
        onClose = {}
    )
}

@Composable
fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier
                .weight(1F)
                .padding(start = 16.dp),
            text = taskName
        )

        Checkbox(checked = checked, onCheckedChange = onCheckedChange)

        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        StatefulCounter()
        WellnessTasksList()
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(modifier = modifier, count = count, onIncrement = {
        count++
    })
}

@Composable
fun StatelessCounter(modifier: Modifier = Modifier, count: Int, onIncrement: () -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String,
    onClose: () -> Unit,
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessScreenPreview() {
    BasicStateCodelabTheme {
        WellnessScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessTaskItemPreview() {
    BasicStateCodelabTheme {
        WellnessTaskItem(taskName = "hihi", onClose = {})
    }
}
