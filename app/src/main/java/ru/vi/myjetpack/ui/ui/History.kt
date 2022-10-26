package ru.vi.myjetpack.ui.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import ru.vi.myjetpack.MainViewModel
import ru.vi.myjetpack.databases.History
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun History(model: MainViewModel,)
{
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val allProducts by model.allHistory.observeAsState(listOf())
    var size = allProducts.size

    LazyColumn(
        Modifier

            .fillMaxWidth()
            .padding(10.dp),
        state = listState

    ) {

        val list = allProducts
        item {
            Log.d("mylog","TitleRow")
            TitleRow( head1 = "Text", head2 = "Date")
        }

        items(list) {
                history ->
            Log.d("mylog","add item"+history.Text)


        ProductRow(history)
        }
        Log.d("mylog","coroutineScope")
       coroutineScope.launch {
           listState.animateScrollToItem(size-1)
        }
    }
}


@Composable
fun TitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier

            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.2f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f))

    }
}

@Composable
fun ProductRow(history: History) {
    Row(
        modifier = Modifier

             .fillMaxWidth()
            .padding(5.dp)
    ) {

        Text(history.Text, modifier = Modifier.weight(0.2f))
        Text(history.Date, modifier = Modifier.weight(0.2f))
    }
}