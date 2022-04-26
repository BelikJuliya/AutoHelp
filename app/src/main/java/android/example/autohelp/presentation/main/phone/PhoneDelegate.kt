package android.example.autohelp.presentation.main.phone

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemEventTypeBinding
import android.example.autohelp.databinding.ItemPhoneBinding
import android.example.autohelp.domain.main.phone.Phone
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.view.ViewGroup

class PhoneViewHolder(
    val parent: ViewGroup,
    val confirm: (phone: String) -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_phone) {

    private lateinit var binding: ItemPhoneBinding
//    private lateinit var maskListener: MaskedTextChangedListener

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemPhoneBinding.bind(itemView)
        with(binding) {
            model as Phone
            model.phoneNumber?.let {
                etPhoneInput.setText(it)
            }
        }
    }

    private fun bindPhone(model: BaseModel) {
        model as Phone
        with(binding) {
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.PHONE_NUMBER -> bindPhone(model)
            }
        }
    }
}

class PhoneDelegate(
    private val confirm: (phone: String) -> Unit = {}
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        PhoneViewHolder(
            parent,
            confirm
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is Phone
}