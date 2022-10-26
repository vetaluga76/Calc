package ru.vi.myjetpack.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HistoryDao {

    @Query("SELECT * FROM History")
    fun getAll(): LiveData<List<History>>

    @Insert
    fun insertRecord(history:History)

    @Query("DELETE FROM History")
    fun deleteAll()

}