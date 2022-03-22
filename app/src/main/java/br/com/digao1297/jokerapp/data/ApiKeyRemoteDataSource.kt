package br.com.digao1297.jokerapp.data

import android.content.SharedPreferences
import br.com.digao1297.jokerapp.data.httpClient.ChuckNorrisAPI
import br.com.digao1297.jokerapp.data.httpClient.HTTPClient
import org.json.JSONObject
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiKeyRemoteDataSource() {

    private val sharedPreferences: SharedPreferences by inject(SharedPreferences::class.java)
    private val editor: SharedPreferences.Editor by inject(SharedPreferences.Editor::class.java)


    companion object {
        const val API_KEY: String = "api_key"
    }

    fun setLocalApiKey(apiKey: String) {
        editor.putString(API_KEY, apiKey)
        editor.apply()
    }

    fun getLocalApiKey(): String? {
        return sharedPreferences.getString(API_KEY, null)
    }

    fun getRemoteApiKey(callback: DefaultCallback<String>, email: String) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .getApiKey(email)
            .enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if (response.isSuccessful) {
                        response.body().let { apikey ->
                            callback.onSuccess(apikey ?: "")
                        }
                    } else {
                        val error = response.errorBody()?.string()
                        val jsonResponse = JSONObject(error)
                        callback.onError(jsonResponse["message"].toString() ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }

            })
    }


}