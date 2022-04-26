package android.example.autohelp.presentation.main.car

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemCulpritCarNumberBinding
import android.example.autohelp.domain.main.Car
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.example.autohelp.presentation.base.VehicleType
import android.view.ViewGroup

class CarNumberViewHolder(
    val parent: ViewGroup,
    private val handleCarNumber: (carNumber: String) -> Unit = {},
    private val handleVehicleType: (vehicleType: VehicleType) -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_culprit_car_number) {

    private lateinit var binding: ItemCulpritCarNumberBinding

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemCulpritCarNumberBinding.bind(itemView)
        with(binding) {
            model as Car
            model.firstLetter?.let { etFirstLetter.setText(it) }
            model.number?.let { etCarNumber.setText(it) }
            model.lastLetters?.let { etLastLetters.setText(it) }
            model.region?.let { etCarRegion.setText(it) }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                handleVehicleType(if (checkedId == R.id.rb_auto) VehicleType.AUTO else VehicleType.MOTO)
            }
        }
    }

    private fun bindFirstLetter(model: BaseModel) {
        model as Car
        with(binding) {
            etFirstLetter.setText(model.firstLetter)
        }
    }

    private fun bindNumber(model: BaseModel) {
        model as Car
        with(binding) {
            etCarNumber.setText(model.number)
        }
    }

    private fun bindLastLetters(model: BaseModel) {
        model as Car
        with(binding) {
            etLastLetters.setText(model.lastLetters)
        }
    }

    private fun bindRegion(model: BaseModel) {
        model as Car
        with(binding) {
            etCarRegion.setText(model.region)
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.FIRST_LETTER -> bindFirstLetter(model)
                PostModelPayload.NUMBER -> bindNumber(model)
                PostModelPayload.LAST_LETTERS -> bindLastLetters(model)
                PostModelPayload.REGION -> bindRegion(model)
            }
        }
    }
}

class CarNumberDelegate(
    private val handleCarNumber: (carNumber: String) -> Unit = {},
    private val handleVehicleType: (vehicleType: VehicleType) -> Unit = {}
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        CarNumberViewHolder(
            parent,
            handleCarNumber,
            handleVehicleType
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is Car
}
