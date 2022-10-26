package ru.vi.myjetpack

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*

import androidx.compose.ui.tooling.preview.Preview

import ru.vi.myjetpack.ui.theme.MyjetpackTheme
import ru.vi.myjetpack.ui.ui.Numbers
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import ru.vi.myjetpack.databases.History
import ru.vi.myjetpack.ui.ui.History


import ru.vi.myjetpack.ui.ui.Top


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mylog", "MainActivity onCreate")

        setContent {
            DefaultPreview()
        }

       // val  dataSource: CalcHistoryDao = HistoryDatabase.getInstance(application).getCalcHistoryDao()
        //val viewModelFactory = HistoryViewModelFactory(dataSource, application)
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview(model: MainViewModel? = LocalViewModelStoreOwner.current?.let { viewModel(it,"MainViewModel",MainViewModelFactory(LocalContext.current.applicationContext as Application)) }) {
    MyjetpackTheme {
        Column {
            Top(model = model!!)
            Numbers(model = model)
            History(model = model)
        }
    }

}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview(model: MainViewModel = viewModel()) {
    Log.d("mylog","MainActivity DefaultPreview")
    MyjetpackTheme {
        MainScreen(
            model = model,

        )
    }
}
*/
/*
@Composable
fun MainScreen(
    model: MainViewModel,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {



        val onTextChange = { text : String ->
            model.text = text

        }

        Text("Temperature Converter",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        InputRow(model)

        Text(model.result,
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = { model.convertTemp() }
        )
        {
            Text("Convert Temperature")
        }
        Button(
            onClick = { model.isFahrenheit= !model.isFahrenheit}
        )
        {
            Text("F to C")
        }
    }
}

@Composable
fun InputRow(model: MainViewModel) {
    Row(verticalAlignment = Alignment.CenterVertically) {


        BasicTextField(

            value = model.text,
            onValueChange = { model.text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),

            )

        Crossfade(
            targetState = model.isFahrenheit,
            animationSpec = tween(2000),

        ) {
                visible ->
            when (visible) {
                true -> Text("\u2109", style = MaterialTheme.typography.bodyLarge)
                false -> Text("\u2103", style = MaterialTheme.typography.bodyLarge)
            }

        }
    }
}
*/