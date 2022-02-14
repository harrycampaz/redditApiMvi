package com.harrycampaz.core.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.harrycampaz.core.data.models.DataPostsDb

@Database(entities = [DataPostsDb::class], version = DatabaseHelper.DATABASE_VERSION)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun postDao(): DataPostsDao

    companion object {
        private var instance: DatabaseHelper? = null

        const val DATABASE_VERSION = 1
        val DATABASE_NAME = "redditposts.db"
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseHelper::class.java,
                DATABASE_NAME
            )
                .addCallback(callback)
                .build()

        private val callback: Callback = object : Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                Log.d("DB Create: ", db.path)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.d("DB Open: ", db.path)
            }
        }
    }
}
