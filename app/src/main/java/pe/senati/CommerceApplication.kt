package pe.senati

import android.app.Application
import androidx.room.Room

class CommerceApplication: Application()
{
    // para que database sea visible desde cualquier vista
    companion object{
        lateinit var database:CommerceDatabase
    }

    //ejecutarse al aparecer la vista principal o al ejecutar la aplicacion se crea esto
    override fun onCreate()
    {
        super.onCreate()
        database= Room.databaseBuilder(this, CommerceDatabase::class.java, "CommerceDatabase").build()
    }
}