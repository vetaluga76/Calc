package ru.vi.myjetpack.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [History::class],version = 1)

//@Database(entities = [History::class],version = 2)

abstract class HistoryDatabase : RoomDatabase()
{
    abstract fun historyDao(): HistoryDao


    companion object {


        @Volatile private var instance: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            synchronized(this) {
                var inst = instance

                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "product_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    instance = inst
                }
                return inst
            }
        }

        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

      /*  private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE History ADD COLUMN date TEXT DEFAULT '' NOT NULL")
            }
        }*/

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            HistoryDatabase::class.java, "history.db")
           // .addMigrations(MIGRATION_1_2)
            .build()
    }
}