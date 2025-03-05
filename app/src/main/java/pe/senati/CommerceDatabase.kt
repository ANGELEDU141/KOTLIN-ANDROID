package pe.senati

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CommerceEntity::class), version=1)
abstract class CommerceDatabase :RoomDatabase()
{
    public abstract fun commerceDao(): CommerceDao
}