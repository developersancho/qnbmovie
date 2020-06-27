package com.developersancho.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.developersancho.local.converter.IntArrayListConverter
import com.developersancho.local.dao.MovieDao
import com.developersancho.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntArrayListConverter::class)
abstract class CoreDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}