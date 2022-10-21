package com.cesor.android.eventsprueba3.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesor.android.eventsprueba3.domain.*
import kotlinx.coroutines.launch

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.ui
 * Created by: César Castro on 14/10/2022 at 19:49.
 ***/
class EventViewModel : ViewModel() {
    private val getAllEventsUseCase = GetAllEventsUseCase()
    private val insertEventUseCase = InsertEventUseCase()
    private val deleteEventsUseCase = DeleteEventsUseCase()
    private val deleteEventUseCase = DeleteEventUseCase()
    private val updateEventUseCase = UpdateEventUseCase()

    val eventList = MutableLiveData<MutableList<Event>?>()
    val event = MutableLiveData<Event>()
    val isEditMode = MutableLiveData(false)
    val isFabVisible = MutableLiveData(true)

    fun setEventList() {
        viewModelScope.launch {
            val events = getAllEventsUseCase()
            //Funcionaba con !events.isNullOrEmpty(
            if (events.isNotEmpty()) {
                eventList.postValue(events)
            } else eventList.postValue(mutableListOf())
        }
    }

    fun insertEvents(event: Event) {
        viewModelScope.launch {
            insertEventUseCase(event)
            refreshEventList()
        }
    }

    private fun refreshEventList() {
        viewModelScope.launch {
            eventList.postValue(getAllEventsUseCase())
        }
    }

    fun deleteAllEvents() {
        viewModelScope.launch {
            deleteEventsUseCase()
            refreshEventList()
        }
    }
    fun deleteEvent(event: Event){
        viewModelScope.launch {
            deleteEventUseCase(event)
            refreshEventList()
        }
    }
    fun updateEvent(event: Event){
        viewModelScope.launch {
            updateEventUseCase(event)
            refreshEventList()
        }
    }
}