package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.domain.main.event.Event
import android.example.autohelp.domain.main.event.EventTypesList
import android.example.autohelp.domain.main.phone.Phone
import android.example.autohelp.presentation.base.BaseModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
) : ViewModel() {

    val itemList = MutableLiveData<MutableList<BaseModel>>()
    private val eventList = mutableListOf<BaseModel>()
    private var selectedEventType: Event? = null

    fun populateList() {
        itemList.value = mutableListOf(
            EventTypesList(eventList.apply {
                add(Event(R.string.parking_title, R.drawable.ic_parking))
                add(Event(R.string.evacuation_title, R.drawable.ic_evacuation))
                // TODO add road accident event
            }),
            Phone()
        )
    }


    fun handleEventTypeSelection(model: Event) {
        val newEventList = eventList.map {
            if (it is Event) {
                if (model == it) {
                    it.copy(isSelected = true)
                } else {
                    it.copy(isSelected = false)
                }
            } else it
        }
        val newItems = itemList.value?.toMutableList() ?: mutableListOf()
        itemList.value = newItems.map {
            if (it is EventTypesList) {
                it.copy(eventList = newEventList.toMutableList())
            } else it
        }.toMutableList()
        selectedEventType = model
    }
}