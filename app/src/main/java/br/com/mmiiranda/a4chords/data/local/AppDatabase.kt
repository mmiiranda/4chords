package br.com.mmiiranda.a4chords.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mmiiranda.a4chords.data.local.dao.SongDao
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity

@Database(entities = [SongEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}
