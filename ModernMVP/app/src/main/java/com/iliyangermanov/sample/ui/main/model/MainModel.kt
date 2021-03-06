package com.iliyangermanov.sample.ui.main.model

import android.os.Handler
import com.iliyangermanov.modernmvp.DataCallback
import com.iliyangermanov.sample.ui.main.MainContract

class MainModel : MainContract.Model {
    override fun fetchGreeting(name: String, callback: DataCallback<String>) {
        Handler().postDelayed({
            callback.onSuccess("Hello, $name!")
        }, 1000)
    }
}