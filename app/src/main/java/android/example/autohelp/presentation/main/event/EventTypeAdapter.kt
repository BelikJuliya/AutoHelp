package android.example.autohelp.presentation.main.event

import android.example.autohelp.domain.main.event.Event
import android.example.autohelp.presentation.base.BaseRecyclerAdapter

class EventTypeAdapter(
    selectEvent: (event: Event) -> Unit = {},
) : BaseRecyclerAdapter(
    listOf(
        EventDelegate(selectEvent)
    )
)