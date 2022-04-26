package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.domain.main.Event
import android.example.autohelp.domain.main.EventTypesList
import android.example.autohelp.presentation.base.BaseModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {

    val itemList = MutableLiveData<MutableList<BaseModel>>()
    private val eventList = mutableListOf<BaseModel>()

    fun populateList() {
        itemList.value = mutableListOf(
            EventTypesList(eventList.apply {
                add(Event(R.string.parking_title, R.drawable.ic_parking))
                add(Event(R.string.evacuation_title, R.drawable.ic_evacuation))
                // TODO add road accident event
            })
        )
    }
}