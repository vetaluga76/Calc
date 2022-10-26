package ru.vi.myjetpack.ui.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vi.myjetpack.MainViewModel

private val numberColumns = listOf(
    listOf("C","<-","Hist","ClHist"),
    listOf("7", "4", "1", "0"),
    listOf("8", "5", "2", "."),
    listOf("9", "6", "3", "="),
    listOf("*", "/", "-", "+")

)

@Composable()
fun Numbers(model: MainViewModel,) {

    androidx.compose.material3.Surface() {
        Row {
            numberColumns.forEach { numberColumn ->
                Column {
                    numberColumn.forEach { t -> Button(onClick = {model.bClick(t)}) { Text(text = t) }}
                    }
                Spacer(modifier =  Modifier.width(10.dp))
            }
        }
    }
}


