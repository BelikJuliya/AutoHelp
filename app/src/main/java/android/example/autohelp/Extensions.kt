package android.example.autohelp

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View

fun View?.visible(state: Boolean?) {
    this ?: return
    when (state) {
        true -> if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) this.visibility = View.VISIBLE
        false -> if (this.visibility == View.VISIBLE) this.visibility = View.GONE
        else -> if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
    }
}

fun getRealPathFromURIPath(contentURI: Uri, context: Context): String {
    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

    context.contentResolver.query(contentURI, filePathColumn, null, null, null).use {
        return if (it == null) {
            contentURI.path.orEmpty()
        } else {
            try {
                it.moveToFirst()
                val idx = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                it.getString(idx)
            } catch (ex: Throwable) {
                ex.message?.let { it1 -> Log.e("TAG", it1) }
                ""
            }
        }
    }
}