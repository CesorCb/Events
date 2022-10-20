package com.cesor.android.eventsprueba3.domain

import com.cesor.android.eventsprueba3.data.EventRepository
import com.cesor.android.eventsprueba3.data.database.entities.toDatabase

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.domain
 * Created by: César Castro on 14/10/2022 at 22:17.
 ***/
class DeleteEventUseCase {
    private val repository = EventRepository()

    suspend operator fun invoke(event: Event){
        repository.deleteEvent(event.toDatabase())
    }
}