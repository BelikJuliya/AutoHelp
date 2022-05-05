package android.example.autohelp.presentation.main.video

import android.example.autohelp.R
import android.example.autohelp.databinding.ItemAccidetDescriptionBinding
import android.example.autohelp.domain.video.AccidentDescription
import android.example.autohelp.domain.video.Video
import android.example.autohelp.presentation.base.AdapterDelegate
import android.example.autohelp.presentation.base.BaseModel
import android.example.autohelp.presentation.base.BaseViewHolder
import android.example.autohelp.presentation.base.PostModelPayload
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator

class UploadFilesViewHolder(
    val parent: ViewGroup,
    private val upload: (video: Video) -> Unit = {},
    private val delete: (video: Video) -> Unit = {},
    private val recordVideo: () -> Unit = {}
) : BaseViewHolder(parent, R.layout.item_accidet_description) {

    private lateinit var binding: ItemAccidetDescriptionBinding

    private val videoAdapter by lazy {
        VideoAdapter(
            delete = {
                delete(it)
            }
        )
    }

    override fun bind(model: BaseModel, viewHolder: BaseViewHolder) {
        binding = ItemAccidetDescriptionBinding.bind(itemView)
        with(binding) {
            model as AccidentDescription
            (rvVideos.itemAnimator as SimpleItemAnimator?)?.supportsChangeAnimations = false
            if (rvVideos.adapter == null)
                rvVideos.adapter = videoAdapter
            videoAdapter.submitList(model.videoList)

            tvRecordVideo.setOnClickListener { recordVideo() }

//            tvUploadVideo.setOnClickListener { upload(model) }
        }
    }

    private fun bindItems(model: BaseModel) {
        model as AccidentDescription
        with(binding) {
            (rvVideos.itemAnimator as SimpleItemAnimator?)?.supportsChangeAnimations = false
            if (rvVideos.adapter == null)
                rvVideos.adapter = videoAdapter
            videoAdapter.submitList(model.videoList)
        }
    }

    private fun bindDescription(model: BaseModel) {
        model as AccidentDescription
        with(binding) {
            etDescription.setText(model.description)
        }
    }

    override fun bindPayload(
        model: BaseModel,
        viewHolder: BaseViewHolder,
        payloads: MutableList<Any>
    ) {
        payloads.forEach {
            when (it) {
                PostModelPayload.VIDEO_LIST -> bindItems(model)
                PostModelPayload.DESCRIPTION -> bindDescription(model)
            }
        }
    }
}

class UploadFilesDelegate(
    private val upload: (video: Video) -> Unit = {},
    private val delete: (video: Video) -> Unit = {},
    private val recordVideo: () -> Unit = {}
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder =
        UploadFilesViewHolder(
            parent,
            upload,
            delete,
            recordVideo
        )

    override fun isValidType(baseModel: BaseModel): Boolean = baseModel is AccidentDescription
}