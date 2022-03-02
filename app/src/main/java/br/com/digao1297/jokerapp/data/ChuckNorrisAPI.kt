package br.com.digao1297.jokerapp.data

import br.com.digao1297.jokerapp.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisAPI {

    @GET("jokes/categories")
    fun findAllCategories(@Query("apiKey") apiKey: String = HTTPClient.API_KEY): Call<List<String>>

    @GET("jokes/random")
    fun findRandomJoke(@Query("apiKey") apiKey: String = HTTPClient.API_KEY, @Query("category") category:String):Call<Joke>

    @GET("jokes/random")
    fun findTodayJoke(@Query("apiKey") apiKey: String = HTTPClient.API_KEY):Call<Joke>
}