package com.example.dwkim.viewmodel

import com.example.dwkim.repository.IRepository
import com.example.dwkim.util.rx.RxViewModel
import com.example.dwkim.util.rx.SchedulerProvider

class UserViewModel(
    private val repository: IRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {

}