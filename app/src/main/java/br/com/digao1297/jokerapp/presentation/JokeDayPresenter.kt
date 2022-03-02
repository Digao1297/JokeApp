package br.com.digao1297.jokerapp.presentation

import br.com.digao1297.jokerapp.data.DefaultCallback
import br.com.digao1297.jokerapp.data.JokeDayRemoteDataSource
import br.com.digao1297.jokerapp.model.Joke
import br.com.digao1297.jokerapp.view.JokeDayFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource
): DefaultCallback<Joke> {

    fun findTodayJoke() {
        view.showProgressBar()
        dataSource.findTodayJoke(this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }
}