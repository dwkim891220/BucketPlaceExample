package com.example.dwkim.view.fragment

import androidx.lifecycle.Observer
import com.example.dwkim.R
import com.example.dwkim.util.showDialog
import com.example.dwkim.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.android.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment() {
    companion object {
        val TAG = SignInFragment::class.java.simpleName
        fun newInstance(): SignInFragment = SignInFragment().init() as SignInFragment
    }

    private val viewModel: UserViewModel by viewModel()

    override fun createLayout(): Int = R.layout.fragment_signin
    override fun setLayouts() {
        initObserver()

        btn_fSignIn_signIn.setOnClickListener {
            viewModel.signIn(
                et_fSignIn_nickName.text.toString(),
                et_fSignIn_pwd.text.toString()
            )
        }
    }

    private fun initObserver(){
        viewModel.states.observe(this, Observer { state ->
            when(state){
                is UserViewModel.CompleteSignIn -> {
                    listener?.successLogin()
                }
            }
        })

        viewModel.errorState.observe(this, Observer {
            context?.showDialog("에러가 발생 했습니다.")
        })
    }
}