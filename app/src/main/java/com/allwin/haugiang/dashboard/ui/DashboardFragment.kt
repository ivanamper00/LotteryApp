package com.allwin.haugiang.dashboard.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.allwin.haugiang.R
import com.allwin.haugiang.common.base.BaseFragment
import com.allwin.haugiang.common.binding.viewBinding
import com.allwin.haugiang.common.state.UiEvent
import com.allwin.haugiang.databinding.FragmentDashboardBinding
import com.allwin.haugiang.feeds.data.dto.response.FeedsModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment: BaseFragment(R.layout.fragment_dashboard),
    DashboardController.Listener {

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private val viewModel by viewModels<DashboardViewModel>()

    private val controller by lazy {
        DashboardController(this)
    }

    override fun setupViews() {
        viewModel.getFeeds()
        with(binding){
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.setController(controller)
        }
    }

    override fun viewModelObservers() {
        viewModel.uiEvent.observe(viewLifecycleOwner){ event ->
            when(event){
                is UiEvent.Loading -> showLoading(event.isLoading)
                is UiEvent.Error -> showToast(event.error.localizedMessage ?: "")
                is DashboardEvent.Feeds -> controller.data = event.data
            }
        }
    }

    override fun onFeedsClick(item: FeedsModel.Channel.Item) {
        val directions = DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(Gson().toJson(item))
        navController.navigate(directions)
    }

}