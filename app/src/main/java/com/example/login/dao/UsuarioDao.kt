package com.example.login.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.login.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert
    fun insert (usuario: Usuario): Long

    @Update
    fun update (usuario: Usuario)

    @Upsert
    suspend fun upsert (usuario: Usuario): Long

    @Query ("select * from usuario p order by p.user")
    fun getAll(): Flow<List<Usuario>>

    @Query("select * from usuario p where p.id = :id")
    fun findById(id: Long): Usuario?

    @Delete
    fun delete(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE user = :user AND senha = :senha LIMIT 1")
    fun getUserByCredentials(user: String, senha: String): Flow<Usuario?>

}