package com.example.dwkim.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dwkim.R
import com.example.dwkim.util.showDialog
import com.example.dwkim.view.adapter.CardListAdapter
import com.example.dwkim.view.adapter.UserListAdapter
import com.example.dwkim.viewmodel.HomeViewModel
import com.example.dwkim.viewmodel.HomeViewModel.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    companion object {
        val TAG = HomeFragment::class.java.simpleName
        fun newInstance(): HomeFragment = HomeFragment().init() as HomeFragment
    }

    private val viewModel: HomeViewModel by viewModel()
    private var popularCardAdapter: CardListAdapter? = null
    private val popularUserList = UserListAdapter()

    override fun createLayout(): Int = R.layout.fragment_home

    override fun setLayouts() {
        initObserver()

        srl_fHome.setOnRefreshListener {
            popularCardAdapter?.clear()
            popularUserList.clear()
            viewModel.getHome()
            srl_fHome.isRefreshing = false
        }

        rv_fHome_popularCards.run {
            popularCardAdapter = CardListAdapter(Glide.with(this@HomeFragment)).apply {
                onClick = { id ->
                    listener?.showCardDetail(id)
                }
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = popularCardAdapter
        }

        rv_fHome_popularUsers.run{
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = popularUserList.apply {
            onClick = { id ->
                    listener?.showUserDetail(id)
                }
            }
        }

        viewModel.getHome()
    }

    private fun initObserver(){
        viewModel.states.observe(this, Observer { state ->
            when(state){
                is AddPopularUserList -> popularUserList.addAll(state.list)
                is AddPopularCardList -> popularCardAdapter?.addAll(state.list)
                is EmptyPopularUserList -> { }
                is EmptyPopularCardList -> { }
            }
        })

        viewModel.errorState.observe(this, Observer {
            context?.showDialog("에러가 발생 했습니다.")
        })
    }
}