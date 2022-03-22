package br.com.digao1297.jokerapp.data.httpClient

import br.com.digao1297.jokerapp.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChuckNorrisAPI {

    @GET("jokerapp/jokes/categories")
    fun findAllCategories(): Call<List<String>>

    @GET("jokerapp/jokes/random")
    fun findRandomJoke(
        @Query("category") category: String
    ): Call<Joke>

    @GET("jokerapp/jokes/random")
    fun findTodayJoke(): Call<Joke>

    @GET("client/{email}")
    fun getApiKey(@Path("email") email: String): Call<String>
}