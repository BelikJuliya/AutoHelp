package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemEventBinding
import android.example.autohelp.domain.main.Event
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.view.ViewGroup
import androidx.core.content.ContextCompat

class EventViewHolder(
    val parent: ViewGroup,
    val selectEventType: (event: Event) -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_event) {

    private lateinit var binding: ItemEventBinding

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemEventBinding.bind(itemView)
        with(binding) {
            model as Event
            ivIcon.setImageResource(model.iconRes)
            tvTitle.text = itemView.resources.getString(model.titleRes)
            if (model.isSelected) {
                ivIcon.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.main_yellow),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                clFrame.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_event_type_selected)
            } else {
                ivIcon.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.main_dark),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                clFrame.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_white_corners_9)
            }
            root.setOnClickListener {
                selectEventType(model)
            }
        }
    }

    private fun bindTitle(model: BaseModel) {
        model as Event
        with(binding) {
            tvTitle.text = itemView.resources.getString(model.titleRes)
        }
    }

    private fun bindIcon(model: BaseModel) {
        model as Event
        with(binding) {
            ivIcon.setImageResource(model.iconRes)
        }
    }

    private fun bindIsSelected(model: BaseModel) {
        model as Event
        with(binding) {
            if (model.isSelected) {
                ivIcon.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.main_yellow),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                clFrame.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_event_type_selected)
            } else {
                ivIcon.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.main_dark),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                clFrame.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_white_corners_9)
            }
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.EVENT -> bindTitle(model)
                PostModelPayload.ICON -> bindIcon(model)
                PostModelPayload.IS_SELECTED -> bindIsSelected(model)
            }
        }
    }
}

class EventDelegate(
    private val selectEventType: (event: Event) -> Unit = {}
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        EventViewHolder(
            parent,
            selectEventType
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is Event
}