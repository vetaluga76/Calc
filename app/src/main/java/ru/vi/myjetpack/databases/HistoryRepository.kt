package ru.vi.myjetpack.databases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class HistoryRepository(private val historyDao: HistoryDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val allHistory: LiveData<List<History>> = historyDao.getAll()

    fun insertRecord(newRecord: History) {
        coroutineScope.launch(Dispatchers.IO) {
            Log.d("mylog", "insertRecord")
            historyDao.insertRecord(newRecord)
        }
    }

    fun deleteAll()
    {
        coroutineScope.launch(Dispatchers.IO)
        {
            historyDao.deleteAll()
        }
    }
}