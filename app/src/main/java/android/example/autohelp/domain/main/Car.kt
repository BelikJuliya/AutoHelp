package android.example.autohelp.domain.main

import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.PostModelPayload

data class Car(
    var firstLetter: String? = null,
    val number: String? = null,
    val lastLetters: String? = null,
    val region: String? = null
) : BaseModel {

    override fun isIdDiff(other: BaseModel): Boolean {
        return other is Car
    }

    override fun isContentDiff(other: BaseModel): Boolean {
        if (other !is Car) return false
        if (other.firstLetter != this.firstLetter) return false
        if (other.number != this.number) return false
        if (other.lastLetters != this.lastLetters) return false
        if (other.region != this.region) return false
        return true
    }

    override fun getPayloadDiff(other: BaseModel): MutableList<Any> {
        val payloads = mutableListOf<Any>()
        if (other !is Car)
            return payloads
        if (other.firstLetter != this.firstLetter)
            payloads.add(PostModelPayload.FIRST_LETTER)
        if (other.number != this.number)
            payloads.add(PostModelPayload.NUMBER)
        if (other.lastLetters != this.lastLetters)
            payloads.add(PostModelPayload.LAST_LETTERS)
        if (other.region != this.region)
            payloads.add(PostModelPayload.REGION)
        return payloads
    }
}
