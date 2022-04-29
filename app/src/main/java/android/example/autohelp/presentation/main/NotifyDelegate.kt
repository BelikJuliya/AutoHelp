package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemNotifyBinding
import android.example.autohelp.domain.Empty
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.view.ViewGroup

class NotifyViewHolder(
    val parent: ViewGroup,
    private val notify: () -> Unit = {},
) : BaseViewHolder(parent, R.layout.item_notify) {

    private lateinit var binding: ItemNotifyBinding

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemNotifyBinding.bind(itemView)
        with(binding) {
            model as Empty
            tvNotify.setOnClickListener {
                notify()
            }
        }
    }
}

class NotifyDelegate(
    private val notify: () -> Unit = {},
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        NotifyViewHolder(
            parent,
            notify
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is Empty
}