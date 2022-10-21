package com.cesor.android.eventsprueba3.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cesor.android.eventsprueba3.domain.Event

/****
 * Project: EventsPrueba2
 * From: com.cesor.android.eventsprueba2.data.database.entities
 * Created by: César Castro on 12/10/2022 at 02:04.
 ***/
@Entity(tableName = "event_table", indices = [androidx.room.Index(value = ["name"], unique = true)])
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "photoUrl") var photoUrl: String = ""
)

fun Event.toDatabase() = EventEntity(id, name, description, date, photoUrl)