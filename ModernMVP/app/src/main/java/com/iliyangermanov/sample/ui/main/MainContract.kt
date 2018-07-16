package com.iliyangermanov.sample.ui.main

import com.iliyangermanov.modernmvp.BasePresenter
import com.iliyangermanov.modernmvp.BaseView
import com.iliyangermanov.modernmvp.DataCallback

interface MainContract {
    interface Presenter : BasePresenter {
        fun showGreeting(name: String)

        fun handleDetailsClick()

        fun handleWeatherClick()
    }

    interface View : BaseView {
        fun showLoading()

        fun displayGreeting(greeting: String)

        fun showError()

        fun openDetailsScreen()

        fun openWeatherScreen()
    }

    interface Model {
        fun fetchGreeting(name: String, callback: DataCallback<String>)
    }
}