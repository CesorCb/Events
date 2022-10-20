package com.cesor.android.eventsprueba3.domain

import com.cesor.android.eventsprueba3.data.EventRepository

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.domain
 * Created by: César Castro on 14/10/2022 at 19:40.
 ***/
class GetAllEventsUseCase {
    private val repository = EventRepository()

    suspend operator fun invoke(): MutableList<Event>{
        return repository.getAllEvents()
    }
}