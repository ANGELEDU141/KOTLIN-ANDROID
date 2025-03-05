package pe.senati;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface CommerceDao
{
    @Insert
    fun insert(commerceEntity: CommerceEntity)

    @Update
    fun update(commerceEntity: CommerceEntity)

    @Delete
    fun delete(commerceEntity: CommerceEntity)

    @Query("select * from CommerceTable where commerceId=:commcerId")
    fun findById(commcerId:Long): CommerceEntity

    @Query("select * from CommerceTable")
    fun findAll(): MutableList<CommerceEntity>
}
