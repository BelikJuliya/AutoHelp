package android.example.autohelp.presentation.main

import android.example.autohelp.presentation.base.BaseRecyclerAdapter
import android.example.autohelp.presentation.base.EventType

class MainScreenAdapter(
    select: (event: EventType) -> Unit = {}
) : BaseRecyclerAdapter(
    listOf(
        EventTypeDelegate(select),
    )
)