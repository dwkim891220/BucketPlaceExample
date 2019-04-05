package com.example.dwkim.view.fragment

import androidx.lifecycle.Observer
import com.example.dwkim.R
import com.example.dwkim.util.showDialog
import com.example.dwkim.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_join.*
import org.koin.android.viewmodel.ext.android.viewModel

class JoinFragment : BaseFragment() {
    companion object {
        val TAG = JoinFragment::class.java.simpleName
        fun newInstance(): JoinFragment = JoinFragment().init() as JoinFragment
    }

    private val viewModel: UserViewModel by viewModel()

    override fun createLayout(): Int = R.layout.fragment_join
    override fun setLayouts() {
        initObserver()

        btn_fJoin_join.setOnClickListener {
            viewModel.join(
                et_fJoin_nickName.text.toString(),
                et_fJoin_introduction.text.toString(),
                et_fJoin_pwd.text.toString()
            )
        }
    }

    private fun initObserver(){
        viewModel.states.observe(this, Observer { state ->
            when(state){
                is UserViewModel.CompleteJoin -> {
                    listener?.successLogin()
                }
            }
        })

        viewModel.errorState.observe(this, Observer {
            context?.showDialog("에러가 발생 했습니다.")
        })
    }
}