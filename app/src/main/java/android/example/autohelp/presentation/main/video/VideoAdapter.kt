package android.example.autohelp.presentation.main.video

import android.example.autohelp.domain.video.Video
import android.example.autohelp.presentation.base.BaseRecyclerAdapter

class VideoAdapter(
    delete: (video: Video) -> Unit = {},
) : BaseRecyclerAdapter(
    listOf(
        VideoDelegate(delete)
    )
)