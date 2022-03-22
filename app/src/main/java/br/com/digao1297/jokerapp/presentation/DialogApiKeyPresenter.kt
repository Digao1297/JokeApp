package br.com.digao1297.jokerapp.presentation

import br.com.digao1297.jokerapp.data.ApiKeyRemoteDataSource
import br.com.digao1297.jokerapp.data.DefaultCallback
import br.com.digao1297.jokerapp.view.DialogApiKeyFragment

class DialogApiKeyPresenter(
    private val view: DialogApiKeyFragment,
    private val dataSource: ApiKeyRemoteDataSource
) : DefaultCallback<String> {


    fun getApiKey(email: String) {
        view.showProgressBar()
        dataSource.getRemoteApiKey(this, email)
    }

    override fun onSuccess(response: String) {
        view.dismissDialog()
        val apikey: String = response.split(": ").last()
        dataSource.setLocalApiKey(apikey)
        view.refreshHomeFragment()
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }
}