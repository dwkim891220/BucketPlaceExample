package com.example.dwkim.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.dwkim.model.UserModel
import com.example.dwkim.repository.IRepository
import com.example.dwkim.repository.model.User
import com.example.dwkim.util.rx.ErrorState
import com.example.dwkim.util.rx.RxViewModel
import com.example.dwkim.util.rx.SchedulerProvider
import com.example.dwkim.util.rx.ViewModelState
import com.example.dwkim.util.with

class UserViewModel(
    private val repository: IRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {
    val errorState = MutableLiveData<ViewModelState>()
    val states = MutableLiveData<ViewModelState>()

    data class UserState(val user: UserModel) : ViewModelState()
    fun getUser(id: Int){
        launch {
            repository.getUser(id)
                .with(schedulerProvider)
                .subscribe(
                    { user ->
                        states.value = UserState(user)
                    },
                    {
                        errorState.value = ErrorState(it)
                    }
                )
        }
    }

    object CompleteSignIn : ViewModelState()
    fun signIn(nickName: String, pwd: String){
        launch {
            repository.signIn(nickName, pwd)
                .with(schedulerProvider)
                .subscribe(
                    {
                        states.value = CompleteSignIn
                    },
                    {
                        errorState.value = ErrorState(it)
                    }
                )
        }
    }

    object CompleteJoin : ViewModelState()
    fun join(nickName: String, introduction: String, pwd: String){
        launch {
            repository.join(nickName, introduction, pwd)
                .with(schedulerProvider)
                .subscribe(
                    {
                        states.value = CompleteJoin
                    },
                    {
                        errorState.value = ErrorState(it)
                    }
                )
        }
    }
}