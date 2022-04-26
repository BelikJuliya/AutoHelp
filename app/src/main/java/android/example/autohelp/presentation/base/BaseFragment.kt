package android.example.autohelp.presentation.base

import android.example.autohelp.R
import android.example.autohelp.databinding.FragmentBaseRecyclerBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment(R.layout.fragment_base_recycler) {

    open val binding: ViewBinding? = null

    var rootBinding: FragmentBaseRecyclerBinding? = null
//    var titleBinding: TitleViewBinding? = null

    var bindingCreate: () -> View = { View(requireContext()).apply { tag = "default_view" } }

    open fun getLayoutId(): Int? = null

    open fun rootView(): ViewGroup? = rootBinding?.flContainer

//    open fun getTitle(): TitleModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_base_recycler, container, false)
        rootBinding = FragmentBaseRecyclerBinding.bind(view)
        getLayoutId()?.let { layoutId ->
            rootBinding?.flContainer?.removeAllViews()
            val viewRoot = inflater.inflate(layoutId, null).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1f
                )
            }
            bindingCreate = { viewRoot }
            rootBinding?.flContainer?.addView(viewRoot)
        }
        if (getLayoutId() == null) bindingCreate = { view }
//        getTitle()?.let {
//            titleView = TitleView(view.context, titleModel = it).apply {
//                id = View.generateViewId()
//                tag = "title_view"
//                initView(
//                    clickAdd = { clickAdd() },
//                    clickClose = { clickClose() },
//                    clickSearch = { clickSearch() },
//                    clickShare = { clickShare() },
//                    clickSave = { clickSave() },
//                    clickEdit = { clickEdit() },
//                    clickMore = { clickMore() },
//                    clickSettings = { clickSettings() },
//                    clickNotification = { clickNotification() },
//                    clickMessage = { clickMessage() },
//                    clickSearchFull = { clickFullSearch() },
//                    clickToAdditionalMenu = { clickToAdditionalMenu() },
//                    isNight = isNightBaseMainFragment //===============NIGHT MODE=====================
//                )
//            }
//            titleView?.let {
//                titleBinding = TitleViewBinding.bind(it)
//            }
//            rootBinding?.flContainer?.addView(titleView, 0)
//        }
        rootBinding?.refreshLayout?.isEnabled = false
        return view
    }
}
