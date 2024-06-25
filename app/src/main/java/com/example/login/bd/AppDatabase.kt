package com.example.login.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.login.dao.UsuarioDao
import com.example.login.dao.ViagemDao
import com.example.login.entities.DateConverter
import com.example.login.entities.Usuario
import com.example.login.entities.Viagem

@Database(entities = [Usuario::class, Viagem::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val usuarioDao: UsuarioDao
    abstract val viagemDao: ViagemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "viagem-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}