package com.example.dwkim.util.rx

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.example.dwkim.repository.result.BaseResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    fun cleanLaunch(job: () -> Disposable){
        disposables.clear()
        disposables.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}