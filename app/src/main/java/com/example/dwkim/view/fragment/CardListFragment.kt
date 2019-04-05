package com.example.dwkim.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dwkim.R
import com.example.dwkim.util.showDialog
import com.example.dwkim.view.adapter.LargeCardListAdapter
import com.example.dwkim.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.fragment_card_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardListFragment : BaseFragment() {
    companion object {
        val TAG = CardListFragment::class.java.simpleName
        fun newInstance(): CardListFragment = CardListFragment().init() as CardListFragment
    }

    private val viewModel: CardViewModel by viewModel()
    private var cardListAdapter: LargeCardListAdapter? = null

    override fun createLayout(): Int = R.layout.fragment_card_list

    override fun setLayouts() {
        initObserver()

        srl_fCardList.setOnRefreshListener {
            cardListAdapter?.clear()
            viewModel.run {
                initPage()
                getCardList()
            }
            srl_fCardList.isRefreshing = false
        }

        rv_fCardList.run {
            val lm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            cardListAdapter = LargeCardListAdapter(Glide.with(this@CardListFragment))
            layoutManager = lm
            adapter = cardListAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val totalItemCount = lm.itemCount
                    val lastVisibleItem = lm.findLastVisibleItemPosition()

                    if (totalItemCount <= lastVisibleItem + 5) {
                        viewModel.getCardList()
                    }
                }
            })
        }

        viewModel.getCardList()
    }

    private fun initObserver(){
        viewModel.states.observe(this, Observer { state ->
            when(state){
                is CardViewModel.AddCardListState -> cardListAdapter?.addAll(state.list)
                is CardViewModel.EmptyCardListState -> {}
            }
        })

        viewModel.errorState.observe(this, Observer {
            context?.showDialog("에러가 발생 했습니다.")
        })
    }
}