package android.example.autohelp.presentation.main

import android.example.autohelp.domain.main.Event
import android.example.autohelp.presentation.base.BaseRecyclerAdapter

class EventTypeAdapter(
    selectEvent: (event: Event) -> Unit = {},
) : BaseRecyclerAdapter(
    listOf(
        EventDelegate(selectEvent)
    )
)