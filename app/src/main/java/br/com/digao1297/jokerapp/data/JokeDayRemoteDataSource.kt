package br.com.digao1297.jokerapp.data

import br.com.digao1297.jokerapp.data.httpClient.ChuckNorrisAPI
import br.com.digao1297.jokerapp.data.httpClient.HTTPClient
import br.com.digao1297.jokerapp.model.Joke
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeDayRemoteDataSource {

    fun findTodayJoke(callback: DefaultCallback<Joke>) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findTodayJoke()
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        if (joke == null) {
                            callback.onError("Não foi possivel encontrar a piada!")
                        }else {
                            callback.onSuccess(joke)
                        }
                    } else {
                        val error = response.errorBody()?.string()
                        val jsonResponse = JSONObject(error)
                        callback.onError(jsonResponse["message"].toString() ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }
            })
    }
}