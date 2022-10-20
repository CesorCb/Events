package com.cesor.android.eventsprueba3

import android.app.Application
import androidx.room.Room
import com.cesor.android.eventsprueba3.data.database.EventDatabase

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3
 * Created by: César Castro on 14/10/2022 at 19:42.
 ***/
class EventsApp: Application() {

    companion object{
        lateinit var database: EventDatabase
        private const val DATABASE_NAME = "event_database"
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, EventDatabase::class.java, DATABASE_NAME).build()
    }
}