package com.cesor.android.eventsprueba3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesor.android.eventsprueba3.data.database.dao.EventDao
import com.cesor.android.eventsprueba3.data.database.entities.EventEntity

/****
 * Project: EventsPrueba2
 * From: com.cesor.android.eventsprueba2.data.database
 * Created by: César Castro on 12/10/2022 at 02:08.
 ***/
@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao() : EventDao
}