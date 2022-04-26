package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemEventTypeBinding
import android.example.autohelp.domain.main.Event
import android.example.autohelp.domain.main.EventTypesList
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator

class EventTypeViewHolder(
    val parent: ViewGroup,
    val selectEventType: (event: Event) -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_event_type) {

    private lateinit var binding: ItemEventTypeBinding

    private val eventTypeAdapter by lazy {
        EventTypeAdapter(
            selectEvent = {
                selectEventType(it)
            }
        )
    }

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemEventTypeBinding.bind(itemView)
        with(binding) {
            model as EventTypesList
            rvEventType.layoutManager = GridLayoutManager(root.context, 1)
            (rvEventType.itemAnimator as SimpleItemAnimator?)?.supportsChangeAnimations = false
            if (rvEventType.adapter == null)
                rvEventType.adapter = eventTypeAdapter
            eventTypeAdapter.submitList(model.eventList)
        }
    }

    private fun bindItems(model: BaseModel) {
        model as EventTypesList
        with(binding) {
            rvEventType.layoutManager = LinearLayoutManager(root.context)
            (rvEventType.itemAnimator as SimpleItemAnimator?)?.supportsChangeAnimations = false
            if (rvEventType.adapter == null)
                rvEventType.adapter = eventTypeAdapter
            eventTypeAdapter.submitList(model.eventList)
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.EVENT_LIST -> bindItems(model)
            }
        }
    }
}

class EventTypeDelegate(
    private val selectEventType: (event: Event) -> Unit = {}
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        EventTypeViewHolder(
            parent,
            selectEventType
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is EventTypesList
}
