package br.com.mmiiranda.a4chords.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.mmiiranda.a4chords.data.local.dao.SongDao
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity

@Database(
    entities = [SongEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "a4chords.db"
                )
                    .fallbackToDestructiveMigration()

                    .build().also { INSTANCE = it }
            }
        }
    }
}
