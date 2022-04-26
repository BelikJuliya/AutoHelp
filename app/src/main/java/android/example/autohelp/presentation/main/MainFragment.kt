package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.databinding.FragmentMainNewBinding
import android.example.autohelp.presentation.base.BaseFragment
import android.example.autohelp.presentation.base.viewBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private val viewModel by viewModels<MainScreenViewModel>()

    override val binding by viewBinding { FragmentMainNewBinding.bind(bindingCreate()) }

    override fun getLayoutId(): Int = R.layout.fragment_main_new

    private val mainScreenAdapter by lazy {
        MainScreenAdapter(
            select = {
                viewModel.handleEventTypeSelection(it)
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
}