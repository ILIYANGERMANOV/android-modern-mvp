package com.iliyangermanov.sample.ui.details.presenter

import com.iliyangermanov.modernmvp.DataCallback
import com.iliyangermanov.modernmvp.Presenter
import com.iliyangermanov.sample.domain.UserDetails
import com.iliyangermanov.sample.ui.details.DetailsContract

class DetailsPresenter(view: DetailsContract.View, val model: DetailsContract.Model,
                       val userId: String) :
        Presenter<DetailsContract.View>(view), DetailsContract.Presenter {
    override fun loadUserDetails() {
        view?.showLoading()
        model.fetchUserDetails(userId, object : DataCallback<UserDetails> {
            override fun onSuccess(data: UserDetails) {
                val formattedDetails = "name: ${data.name} from ${data.appVersion}"
                view?.displayUserDetails(formattedDetails)
            }

            override fun onError() {
                view?.showError()
            }
        })
    }

}