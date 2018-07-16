package com.iliyangermanov.modernmvp

interface DataCallback<T> {
    fun onSuccess(data: T)

    fun onError()
}