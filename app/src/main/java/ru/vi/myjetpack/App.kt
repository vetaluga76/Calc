package ru.vi.myjetpack

import android.app.Application
import ru.vi.myjetpack.databases.HistoryDatabase


class App : Application()
{

    lateinit var database: HistoryDatabase

    override fun onCreate()
    {
        super.onCreate()
        database = HistoryDatabase.invoke(this)
    }


}