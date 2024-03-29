package com.yacine.listmovieapp.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yacine.listmovieapp.network.ResultState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe { t: T -> data.value = t })
    return data
}
fun <T> PublishSubject<ResultState<T>>.failed(e: Throwable) {
    with(this){
        loading(false)
        onNext(ResultState.failure(e))
    }
}
fun <T> PublishSubject<ResultState<T>>.success(t: T) {
    with(this){
        loading(false)
        onNext(ResultState.success(t))
    }
}
fun <T> PublishSubject<ResultState<T>>.loading(isLoading: Boolean) {
    this.onNext(ResultState.loading(isLoading))
}