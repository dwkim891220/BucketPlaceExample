package com.example.dwkim.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dwkim.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

fun View.show(show: Boolean = true){
    this.visibility = if(show) View.VISIBLE else View.GONE
}

fun View.isVisibility() : Boolean{
    return this.visibility == View.VISIBLE
}

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> =
    observeOn(schedulerProvider.ui())
        .subscribeOn(schedulerProvider.io())

fun Completable.with(schedulerProvider: SchedulerProvider): Completable =
    observeOn(schedulerProvider.ui())
        .subscribeOn(schedulerProvider.io())

fun <T> Fragment.argument(key: String) = lazy { arguments?.get(key) as T}

fun Context.showDialog(contents: String?){
        AlertDialog.Builder(this)
            .setMessage(contents)
            .show()
}