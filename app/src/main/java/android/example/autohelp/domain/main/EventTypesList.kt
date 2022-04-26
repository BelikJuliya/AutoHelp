package android.example.autohelp.domain.main

import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.EventType
import android.example.autohelp.presentation.base.PostModelPayload

data class EventTypesList(
    val eventList: MutableList<BaseModel>
) : BaseModel {

    override fun isIdDiff(other: BaseModel): Boolean {
        return other is EventTypesList
    }

    override fun isContentDiff(other: BaseModel): Boolean {
        if (other !is EventTypesList) return false
        if (other.eventList != this.eventList) return false
        return true
    }

    override fun getPayloadDiff(other: BaseModel): MutableList<Any> {
        val payloads = mutableListOf<Any>()
        if (other !is EventTypesList)
            return payloads
        if (other.eventList != this.eventList)
            payloads.add(PostModelPayload.EVENT_LIST)
        return payloads
    }
}
