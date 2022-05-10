package android.example.autohelp.domain.video

import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.PostModelPayload

data class AccidentDescription(
    var videoList: MutableList<BaseModel>,
//    val description: String
) : BaseModel {

    override fun isIdDiff(other: BaseModel): Boolean {
        return other is AccidentDescription
    }

    override fun isContentDiff(other: BaseModel): Boolean {
        if (other !is AccidentDescription) return false
        if (other.videoList != this.videoList) return false
//        if (other.description != this.description) return false
        return false
    }

    override fun getPayloadDiff(other: BaseModel): MutableList<Any> {
        val payloads = mutableListOf<Any>()
        if (other !is AccidentDescription)
            return payloads
        if (other.videoList != this.videoList)
            payloads.add(PostModelPayload.VIDEO_LIST)
//        if (other.description != this.description)
//            payloads.add(PostModelPayload.DESCRIPTION)
        return payloads
    }
}