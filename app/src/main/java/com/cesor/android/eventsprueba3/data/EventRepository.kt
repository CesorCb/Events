package com.cesor.android.eventsprueba3.data

import com.cesor.android.eventsprueba3.EventsApp
import com.cesor.android.eventsprueba3.data.database.entities.EventEntity
import com.cesor.android.eventsprueba3.domain.Event
import com.cesor.android.eventsprueba3.domain.toDomain

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.data
 * Created by: César Castro on 14/10/2022 at 19:22.
 ***/
class EventRepository {

    suspend fun getAllEvents(): MutableList<Event>{
        val eventList = EventsApp.database.eventDao().getAllEvents()
        return eventList.map { it.toDomain() }.toMutableList()
    }

    suspend fun insertEvent(event: EventEntity){
        EventsApp.database.eventDao().insertEvent(event)
    }

    suspend fun deleteAllEvents(){
        EventsApp.database.eventDao().deleteAllEvents()
    }
    suspend fun deleteEvent(event: EventEntity){
        EventsApp.database.eventDao().deleteEvent(event)
    }
}