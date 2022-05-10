package android.example.autohelp.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.example.autohelp.R
import android.example.autohelp.databinding.FragmentMainBinding
import android.example.autohelp.getRealPathFromURIPath
import android.example.autohelp.presentation.base.BaseFragment
import android.example.autohelp.presentation.base.viewBinding
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbruyelle.rxpermissions3.Permission
import com.tbruyelle.rxpermissions3.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.io.File


@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private val viewModel by viewModels<MainScreenViewModel>()

    override val binding by viewBinding { FragmentMainBinding.bind(bindingCreate()) }

    override fun getLayoutId(): Int = R.layout.fragment_main

    private val rxPermissions by lazy(LazyThreadSafetyMode.NONE) { RxPermissions(this) }

    private val mainScreenAdapter by lazy {
        MainScreenAdapter(
            select = {
                viewModel.handleEventTypeSelection(it)
            },
            confirm = {
                viewModel.confirmPhone(it)
            },
            handleVehicleType = {
                viewModel.handleVehicleType(it)
            },
            upload = {

            },
            recordVideo = {
                checkStoragePermission()
                // dispatchTakeVideoIntent()
            },
            delete = {
                viewModel.deleteVideo(it)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvMain.layoutManager = LinearLayoutManager(activity)
            rvMain.setHasFixedSize(true)
            rvMain.adapter = mainScreenAdapter
        }

        viewModel.populateList()

        viewModel.itemList.observe(viewLifecycleOwner, {
            mainScreenAdapter.submitList(it)
        })
    }

    private fun openVideoActivityForResult() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val videoUri: Uri? = result.data?.data
            var realPath: String? = null
            videoUri?.let { realPath = getRealPathFromURIPath(it, requireContext()) }
            realPath?.let { viewModel.handleVideo(it) }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
//            val videoUri: Uri? = data?.data
//            var realPath: String? = null
//            videoUri?.let { realPath = getRealPathFromURIPath(it, requireContext()) }
//            realPath?.let { viewModel.videoList.add(File(it)) }
//        }
//    }

    @SuppressLint("CheckResult")
    private fun checkStoragePermission() {
        rxPermissions
            .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
            .observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { permission: Permission ->
                when {
                    permission.granted -> {
                        openVideoActivityForResult()
                    }
                    permission.shouldShowRequestPermissionRationale -> {
                        //val errorMessage = getString(R.string.chat_file_system_permission_required)
                        //openDialogWithPermissionError(errorMessage)
                    }
                    else -> {
                        // val errorMessage = getString(R.string.chat_file_system_permission_required_settings)
                        //openDialogWithSettings(errorMessage)
                    }
                }
            }
    }

//    private fun openDialogWithPermissionError(errorMessage: String) {
//        requireContext().showDialog(
//            title = getString(R.string.error),
//            themeResId = R.style.AppTheme_ErrorDialog,
//            message = errorMessage,
//            positiveButtonText = getString(R.string.dialog_good_button_text),
//            negativeSelect = { },
//            positiveSelect = { },
//            cancelListener = { }
//        )
//    }
//
//    private fun openDialogWithSettings(errorMessage: String) {
//        requireContext().showDialog(
//            title = getString(R.string.chat_permission_required_title),
//            themeResId = R.style.AppTheme_ErrorDialog,
//            message = errorMessage,
//            positiveButtonText = getString(R.string.chat_permission_required_settings_action),
//            negativeSelect = { },
//            positiveSelect = {
//                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                val uri = Uri.fromParts(
//                    "package",
//                    requireActivity().applicationContext.packageName,
//                    null
//                )
//                intent.data = uri
//                startActivity(intent)
//            },
//            cancelListener = { }
//        )
//    }

    // Construct a request for phone numbers and show the picker
//    private val RC_HINT = 2
//    @SuppressLint("LongLogTag")
//    private fun requestHint() {
//        val hintRequest = HintRequest.Builder()
//            .setPhoneNumberIdentifierSupported(true)
//            .build()
//
//        val intent = Auth.CredentialsApi.getHintPickerIntent(
//            mCredentialsApiClient, hintRequest)
//
//        try {
//            startIntentSenderForResult(intent.intentSender,
//                RC_HINT, null, 0, 0, 0)
//        } catch (e: Exception) {
//            e.message?.let { Log.e("Error In getting Message", it) }
//        }
//
//    }
//
//    // Obtain the phone number from the result
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_HINT && resultCode == Activity.RESULT_OK) {
//
//            /*You will receive user selected phone number here if selected and send it to the server for request the otp*/
//            var credential: Credential = data?.getParcelableExtra(Credential?.EXTRA_KEY)
//
//        }
//    }

    companion object {
        const val RESOLVE_HINT = 101
        const val REQUEST_VIDEO_CAPTURE = 1
    }
}