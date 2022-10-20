package com.cesor.android.eventsprueba3.domain

import com.cesor.android.eventsprueba3.data.EventRepository

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.domain
 * Created by: César Castro on 14/10/2022 at 21:03.
 ***/
class DeleteEventsUseCase {
    private val repository = EventRepository()

    suspend operator fun invoke(){
        repository.deleteAllEvents()
    }
}