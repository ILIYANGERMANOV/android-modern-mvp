package com.iliyangermanov.sample.ui.weather

import com.iliyangermanov.modernmvp.BasePresenter
import com.iliyangermanov.modernmvp.BaseView
import com.iliyangermanov.modernmvp.DataCallback
import com.iliyangermanov.sample.domain.Weather

interface WeatherContract {
    interface Presenter : BasePresenter {
        fun initScreen()

        fun displayWeather()
    }

    interface View : BaseView {
        fun useCelsius(): Boolean

        fun getCityInput(): String

        fun setCity(city: String)

        fun displayWeather(temp: Double, condition: String)

        fun showError()
    }

    interface Model {
        fun fetchWeather(city: String, callback: DataCallback<Weather>)
    }
}