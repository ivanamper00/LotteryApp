package com.allwin.haugiang.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.allwin.haugiang.common.extensions.showToast
import com.allwin.haugiang.common.ui.dialog.LoadingDialog
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(@LayoutRes private val layout: Int): Fragment() {

    private val loadingDialog by lazy {
        LoadingDialog(requireContext())
    }

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
        viewModelObservers()
        setupViews()
    }

    abstract fun setupViews()

    abstract fun viewModelObservers()

    open fun showLoading(isLoading: Boolean){
        if(isLoading) loadingDialog.show()
        else loadingDialog.dismiss()
    }

    open fun onBackPressed(){
        requireActivity().onBackPressed()
    }

    open fun showToast(message: String){
        requireContext().showToast(message)
    }

    open fun snackToast(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        showLoading(false)
    }
}
