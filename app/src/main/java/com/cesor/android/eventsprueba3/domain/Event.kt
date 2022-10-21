package com.cesor.android.eventsprueba3.domain

import com.cesor.android.eventsprueba3.data.database.entities.EventEntity

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.domain
 * Created by: César Castro on 14/10/2022 at 19:23.
 ***/
data class Event(
    var id: Long = 0,
    var name: String,
    var description: String,
    var date: String,
    var photoUrl: String = ""
)

fun EventEntity.toDomain() = Event(id, name, description, date, photoUrl)
