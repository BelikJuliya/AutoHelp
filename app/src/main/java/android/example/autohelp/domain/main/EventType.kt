package android.example.autohelp.domain.main

import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.PostModelPayload

data class Event(
    val titleRes: Int,
    val iconRes: Int,
    val isSelected: Boolean = false
) : BaseModel {

    override fun isIdDiff(other: BaseModel): Boolean {
        return other is Event
    }

    override fun isContentDiff(other: BaseModel): Boolean {
        if (other !is Event) return false
        if (other.titleRes != this.titleRes) return false
        if (other.iconRes != this.iconRes) return false
        if (other.isSelected != this.isSelected) return false
        return true
    }

    override fun getPayloadDiff(other: BaseModel): MutableList<Any> {
        val payloads = mutableListOf<Any>()
        if (other !is Event)
            return payloads
        if (other.titleRes != this.titleRes)
            payloads.add(PostModelPayload.EVENT)
        if (other.iconRes != this.iconRes)
            payloads.add(PostModelPayload.ICON)
        if (other.isSelected != this.isSelected)
            payloads.add(PostModelPayload.IS_SELECTED)
        return payloads
    }
}
