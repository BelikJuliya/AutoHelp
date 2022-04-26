package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.example.autohelp.databinding.FragmentMainBinding
import android.example.autohelp.databinding.FragmentMainNewBinding
import android.example.autohelp.databinding.ItemEventTypeBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater

import androidx.annotation.NonNull




class MainFragment : Fragment(R.layout.fragment_main_new) {

    val viewModel by viewModels<MainScreenViewModel>()
    private val binding: FragmentMainNewBinding = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJavaPracticeBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

}