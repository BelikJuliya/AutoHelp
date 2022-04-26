package android.example.autohelp.presentation.main

import android.example.autohelp.domain.main.event.Event
import android.example.autohelp.presentation.base.BaseRecyclerAdapter
import android.example.autohelp.presentation.main.event.EventTypeDelegate
import android.example.autohelp.presentation.main.phone.PhoneDelegate

class MainScreenAdapter(
    select: (event: Event) -> Unit = {}
) : BaseRecyclerAdapter(
    listOf(
        EventTypeDelegate(select),
        PhoneDelegate()
    )
)