package br.com.digao1297.jokerapp.data

interface DefaultCallback<in T> {
    fun onSuccess(response: T)

    fun onError(response: String)

    fun onComplete()
}