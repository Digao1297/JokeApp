package br.com.digao1297.jokerapp.data.httpClient

import br.com.digao1297.jokerapp.data.ApiKeyRemoteDataSource
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject

class ApiKeyInterceptor: Interceptor {

    private val apiKeyRemoteDataSource: ApiKeyRemoteDataSource by inject(ApiKeyRemoteDataSource::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request = chain.request()

        val path = request.url.encodedPath
        if(path.startsWith("/client/")){
            return chain.proceed(request)
        }

        val httpUrlBuilder: HttpUrl.Builder =   request.url.newBuilder()

        httpUrlBuilder.addQueryParameter("apiKey", apiKeyRemoteDataSource.getLocalApiKey())

        request = request.newBuilder().url(httpUrlBuilder.build()).build()

        return chain.proceed(request)
    }
}