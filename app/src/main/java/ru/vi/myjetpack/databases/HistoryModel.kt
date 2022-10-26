package ru.vi.myjetpack.databases

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class History
    (

    val Text: String,
    val Date: String,

)
{
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    class List : ArrayList<History>() {

    }
}