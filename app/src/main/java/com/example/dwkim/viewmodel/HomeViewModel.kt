package com.example.dwkim.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.dwkim.model.CardModel
import com.example.dwkim.model.UserModel
import com.example.dwkim.repository.IRepository
import com.example.dwkim.util.rx.ErrorState
import com.example.dwkim.util.rx.RxViewModel
import com.example.dwkim.util.rx.SchedulerProvider
import com.example.dwkim.util.rx.ViewModelState
import com.example.dwkim.util.with

class HomeViewModel(
    private val repository: IRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {
    val states = MutableLiveData<ViewModelState>()
    val errorState = MutableLiveData<ViewModelState>()

    data class AddPopularUserList(val list: List<UserModel>) : ViewModelState()
    object EmptyPopularUserList : ViewModelState()
    data class AddPopularCardList(val list: List<CardModel>) : ViewModelState()
    object EmptyPopularCardList : ViewModelState()

    fun getHome(){
        launch {
            repository.getHome()
                .with(schedulerProvider)
                .subscribe(
                    { result ->
                        result.run {
                            states.value = popularUsers?.run {
                                AddPopularUserList(this)
                            } ?: EmptyPopularUserList

                            states.value = popularCards?.run {
                                AddPopularCardList(this)
                            } ?: EmptyPopularCardList
                        }
                    },
                    {
                        errorState.value = ErrorState(it)
                    }
                )
        }
    }
}