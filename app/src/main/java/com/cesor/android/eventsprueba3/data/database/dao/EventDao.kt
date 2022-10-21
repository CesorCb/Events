package com.cesor.android.eventsprueba3.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cesor.android.eventsprueba3.data.database.entities.EventEntity

/****
 * Project: EventsPrueba2
 * From: com.cesor.android.eventsprueba2.data.database.dao
 * Created by: César Castro on 12/10/2022 at 02:06.
 ***/
@Dao
interface EventDao {
    @Query("SELECT * FROM event_table ORDER BY name DESC")
    suspend fun getAllEvents(): MutableList<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Query("DELETE FROM event_table")
    suspend fun deleteAllEvents()

    @Delete
    suspend fun deleteEvent(event: EventEntity)

    @Update
    suspend fun updateEvent(event: EventEntity)
}