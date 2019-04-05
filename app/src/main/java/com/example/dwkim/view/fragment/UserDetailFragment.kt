package com.example.dwkim.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.dwkim.R
import com.example.dwkim.util.PARAMS_ID
import com.example.dwkim.util.argument
import com.example.dwkim.util.showDialog
import com.example.dwkim.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserDetailFragment : BaseFragment() {
    companion object {
        val TAG = UserDetailFragment::class.java.simpleName
        fun newInstance(id: Int): UserDetailFragment =
            UserDetailFragment().init(
                Bundle().apply{
                    putInt(PARAMS_ID, id)
                }
            ) as UserDetailFragment
    }

    private val viewModel: UserViewModel by viewModel()
    private val userId by argument<Int>(PARAMS_ID)

    override fun createLayout(): Int = R.layout.fragment_user_detail
    override fun setLayouts() {
        initObserver()

        viewModel.getUser(userId)
    }

    private fun initObserver(){
        viewModel.states.observe(this, Observer { state ->
            when(state){
                is UserViewModel.UserState -> {
                    tv_fUserDetail_nickName.text = state.user.nickName
                    tv_fUserDetail_introduction.text = state.user.introduction
                }
            }
        })

        viewModel.errorState.observe(this, Observer {
            context?.showDialog("에러가 발생 했습니다.")
        })
    }
}