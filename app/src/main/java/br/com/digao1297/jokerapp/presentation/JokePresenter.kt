package br.com.digao1297.jokerapp.presentation

import br.com.digao1297.jokerapp.data.DefaultCallback
import br.com.digao1297.jokerapp.data.JokeRemoteDataSource
import br.com.digao1297.jokerapp.model.Category
import br.com.digao1297.jokerapp.model.Joke
import br.com.digao1297.jokerapp.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource
) : DefaultCallback<Joke> {

    fun findRandomJoke(category: Category) {
        view.showProgressBar()
        dataSource.findRandomJoke(this, category.name)
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