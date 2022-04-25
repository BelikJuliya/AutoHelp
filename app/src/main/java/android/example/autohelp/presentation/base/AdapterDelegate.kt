package android.example.autohelp.presentation.base

import android.view.ViewGroup

interface AdapterDelegate {
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun isValidType(baseModel: BaseModel): Boolean
}