package android.example.autohelp.presentation.base

import android.example.autohelp.R
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(R.layout.fragment_main) {

//    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

//    override fun onDestroyView() {
//        super.onDestroyView()
//        KeyboardUtils.hideKeyboard(activity, view?.windowToken)
//    }
//
//    protected fun showSnackbarError(view: View, error: AppError) {
//        SnackbarUtils.showErrorWithOpenActivityAction(view, error) { intent ->
//            startActivity(intent)
//        }
//    }
//
//    protected fun showSnackbarInfo(view: View, msg: String) {
//        SnackbarUtils.show(view, msg)
//    }
}
