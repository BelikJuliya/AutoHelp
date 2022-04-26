package android.example.autohelp.presentation.main

import android.example.autohelp.domain.main.event.Event
import android.example.autohelp.domain.main.phone.Phone
import android.example.autohelp.presentation.base.BaseRecyclerAdapter
import android.example.autohelp.presentation.base.VehicleType
import android.example.autohelp.presentation.main.car.CarNumberDelegate
import android.example.autohelp.presentation.main.event.EventTypeDelegate
import android.example.autohelp.presentation.main.phone.PhoneDelegate

class MainScreenAdapter(
    select: (event: Event) -> Unit = {},
    confirm: (phone: String) -> Boolean = { false },
    handlePhoneInputChange: (phone: Phone) -> Unit = {},
    handleCarNumber: (carNumber: String) -> Unit = {},
    handleVehicleType: (vehicleType: VehicleType) -> Unit = {}

) : BaseRecyclerAdapter(
    listOf(
        EventTypeDelegate(select),
        PhoneDelegate(confirm, handlePhoneInputChange),
        CarNumberDelegate(handleCarNumber, handleVehicleType)
    )
)