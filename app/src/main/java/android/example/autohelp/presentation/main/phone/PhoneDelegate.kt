package android.example.autohelp.presentation.main.phone

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemPhoneConfirmationBinding
import android.example.autohelp.domain.main.phone.Phone
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.example.autohelp.visible
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import com.redmadrobot.inputmask.MaskedTextChangedListener

class PhoneViewHolder(
    val parent: ViewGroup,
    val confirm: (phone: String) -> Boolean = { false },
    val handleInputChange: (phone: Phone) -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_phone_confirmation) {

    private lateinit var binding: ItemPhoneConfirmationBinding
    private lateinit var maskListener: MaskedTextChangedListener
    private var textWatcher: TextWatcher? = null

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemPhoneConfirmationBinding.bind(itemView)
        with(binding) {
            model as Phone
            etPhoneInput.removeTextChangedListener(textWatcher)
            maskListener = MaskedTextChangedListener.installOn(binding.etPhoneInput, "+7 ([000]) [000]-[00]-[00]")
            model.phoneNumber?.let {
                etPhoneInput.setText(it)
            }
            textWatcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    model.phoneNumber = s.toString()
                    handleInputChange(model)
                }
            }
            etPhoneInput.addTextChangedListener(textWatcher)

            if (model.isConfirmed) {
                tvPhoneApproved.text = itemView.resources.getString(R.string.phone_approved)
                btnConfirm.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_approved, 0, 0, 0);
            } else {
                btnConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            tvPhoneApproved.visible(model.isConfirmed)

            btnConfirm.setOnClickListener {
                if (!confirm(etPhoneInput.text.toString())) {
                    etPhoneInput.error = itemView.resources.getString(R.string.phone_error)
                } else {
//                    tvPhoneApproved.text = itemView.resources.getString(R.string.phone_error)
                    tvAttempts.visibility = View.VISIBLE
                    tvSmsCode.visibility = View.VISIBLE
                }
            }
        }
    }

//    private fun bindPhone(model: BaseModel) {
//        model as Phone
//        with(binding) {
//        }
//    }

    private fun bindConfirmed(model: BaseModel) {
        model as Phone
        with(binding) {
            if (model.isConfirmed) {
                tvPhoneApproved.text = itemView.resources.getString(R.string.phone_approved)
                btnConfirm.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_approved, 0, 0, 0)
            } else {
                btnConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            tvPhoneApproved.visible(model.isConfirmed)
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.CONFIRMED -> bindConfirmed(model)
            }
        }
    }
}

class PhoneDelegate(
    private val confirm: (phone: String) -> Boolean = { false },
    private val handleInputChange: (phone: Phone) -> Unit = {}
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        PhoneViewHolder(
            parent,
            confirm,
            handleInputChange
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is Phone
}