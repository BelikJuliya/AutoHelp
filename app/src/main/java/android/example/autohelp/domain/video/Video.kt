package android.example.autohelp.domain.video

import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.PostModelPayload

data class Video(
    val title: String
) : BaseModel {

    override fun isIdDiff(other: BaseModel): Boolean {
        return other is Video
    }

    override fun isContentDiff(other: BaseModel): Boolean {
        if (other !is Video) return false
        if (other.title != this.title) return false
//        if (other.size != this.size) return false
        return true
    }

    override fun getPayloadDiff(other: BaseModel): MutableList<Any> {
        val payloads = mutableListOf<Any>()
        if (other !is Video)
            return payloads
        if (other.title != this.title)
            payloads.add(PostModelPayload.TITLE)
        return payloads
    }
}
