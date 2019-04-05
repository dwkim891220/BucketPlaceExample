package com.example.dwkim.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.dwkim.model.CardDetailModel
import com.example.dwkim.model.CardModel
import com.example.dwkim.repository.IRepository
import com.example.dwkim.repository.result.GetCardResult
import com.example.dwkim.util.rx.ErrorState
import com.example.dwkim.util.rx.RxViewModel
import com.example.dwkim.util.rx.SchedulerProvider
import com.example.dwkim.util.rx.ViewModelState
import com.example.dwkim.util.with

class CardViewModel(
    private val repository: IRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {
    val states = MutableLiveData<ViewModelState>()
    val errorState = MutableLiveData<ViewModelState>()

    val per = 20
    var page = 1

    fun initPage(){
        page = 1
    }

    data class AddCardListState(val list: List<CardModel>) : ViewModelState()
    object EmptyCardListState : ViewModelState()

    fun getCardList(){
        launch {
            repository.getCards(page, per)
                .with(schedulerProvider)
                .subscribe(
                    { result ->
                        states.value = if(result.isEmpty()){
                            EmptyCardListState
                        }else{
                            AddCardListState(result)
                        }
                        page++
                    },
                    {
                        errorState.value = ErrorState(it)
                    }
                )
        }
    }

    data class AddCardDetailState(val result: CardDetailModel) : ViewModelState()
    fun getCard(id: Int){
        launch {
            repository.getCard(id)
                .with(schedulerProvider)
                .subscribe(
                    { result ->
                        states.value = AddCardDetailState(result)
                    },
                    {
                        errorState.value = ErrorState(it)
                    }
                )
        }
    }
}