package com.example.dwkim.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dwkim.R
import com.example.dwkim.model.CardDetailModel
import com.example.dwkim.repository.result.GetCardResult
import com.example.dwkim.util.PARAMS_ID
import com.example.dwkim.util.argument
import com.example.dwkim.util.showDialog
import com.example.dwkim.view.adapter.CardListAdapter
import com.example.dwkim.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.fragment_card_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardDetailFragment : BaseFragment() {
    companion object {
        val TAG = CardDetailFragment::class.java.simpleName
        fun newInstance(id: Int): CardDetailFragment =
            CardDetailFragment().init(
                Bundle().apply{
                    putInt(PARAMS_ID, id)
                }
            ) as CardDetailFragment
    }

    private val viewModel: CardViewModel by viewModel()
    private val userId by argument<Int>(PARAMS_ID)

    private var popularCardAdapter: CardListAdapter? = null

    override fun createLayout(): Int = R.layout.fragment_card_detail
    override fun setLayouts() {
        initObserver()

        popularCardAdapter = CardListAdapter(Glide.with(this@CardDetailFragment)).apply {
            onClick = { id ->
                listener?.showCardDetail(id)
            }
        }

        srl_fCardDetail.setOnRefreshListener {
            popularCardAdapter?.clear()
            viewModel.getCard(userId)

            srl_fCardDetail.isRefreshing = false
        }

        rv_fCardDetail_recommendCards.run {
            adapter = popularCardAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        viewModel.getCard(userId)
    }

    private fun initObserver(){
        viewModel.states.observe(this, Observer { state ->
            when(state){
                is CardViewModel.AddCardDetailState -> {
                    setCardLayout(state.result)
                }
            }
        })

        viewModel.errorState.observe(this, Observer {
            context?.showDialog("에러가 발생 했습니다.")
        })
    }

    private fun setCardLayout(item: CardDetailModel){
        Glide.with(this)
            .load(item.card?.img_url)
            .into(iv_fCardDetail)

        tv_fCardDetail_photoIntroduction.text = String.format("카드설명: %s",item.card?.description)

        tv_fCardDetail_userNickname.text = String.format("작성자: %s",item.user?.nickname)
        tv_fCardDetail_userIntroduction.text = String.format("소개: %s",item.user?.introduction)
        ll_fCardDetail_user.setOnClickListener {
            listener?.showUserDetail(item.user?.id)
        }

        item.recommendCards?.run {
            popularCardAdapter?.addAll(this)
        }
    }
}