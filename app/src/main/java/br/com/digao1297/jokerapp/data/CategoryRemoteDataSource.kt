package br.com.digao1297.jokerapp.data


import br.com.digao1297.jokerapp.data.httpClient.ChuckNorrisAPI
import br.com.digao1297.jokerapp.data.httpClient.HTTPClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {

    fun findAllCategories(callback: DefaultCallback<List<String>>) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                    } else {
                        val error = response.errorBody()?.string()
                        val jsonResponse = JSONObject(error)
                        callback.onError(jsonResponse["message"].toString() ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }

            })
    }

}