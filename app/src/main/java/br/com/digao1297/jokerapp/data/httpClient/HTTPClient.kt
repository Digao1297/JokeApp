package br.com.digao1297.jokerapp.data.httpClient

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object HTTPClient {

    private const val BASE_URL = "https://api.tiagoaguiar.co/"
//    const val API_KEY = "fc20a586-053f-494a-a30e-438df66c326f"


    private fun httpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(ApiKeyInterceptor())
            .build()

    }

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .build()
}