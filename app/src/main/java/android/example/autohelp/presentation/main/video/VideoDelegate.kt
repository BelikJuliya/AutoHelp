package android.example.autohelp.presentation.main.video

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemVideoBinding
import android.example.autohelp.domain.video.Video
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.view.ViewGroup

class VideoViewHolder(
    val parent: ViewGroup,
    val delete: (video: Video) -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_video) {

    private lateinit var binding: ItemVideoBinding

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemVideoBinding.bind(itemView)
        with(binding) {
            model as Video
            tvFileName.setText(model.title)
            ivDelete.setOnClickListener { delete(model) }
        }
    }

    private fun bindTitle(model: BaseModel) {
        model as Video
        with(binding) {
            tvFileName.text = model.title
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.TITLE -> bindTitle(model)
            }
        }
    }
}

class VideoDelegate(
    private val delete: (video: Video) -> Unit
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        VideoViewHolder(
            parent,
            delete
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is Video
}