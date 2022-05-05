package android.example.autohelp.domain.main.phone

import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.PostModelPayload

data class Phone(
    var phoneNumber: String? = null,
    val isConfirmed: Boolean = false
) : BaseModel {

    override fun isIdDiff(other: BaseModel): Boolean {
        return other is Phone
    }

    override fun isContentDiff(other: BaseModel): Boolean {
        if (other !is Phone) return false
        if (other.phoneNumber != this.phoneNumber) return false
        return true
    }

    override fun getPayloadDiff(other: BaseModel): MutableList<Any> {
        val payloads = mutableListOf<Any>()
        if (other !is Phone)
            return payloads
        if (other.phoneNumber != this.phoneNumber)
            payloads.add(PostModelPayload.PHONE_NUMBER)
        return payloads
    }
}
