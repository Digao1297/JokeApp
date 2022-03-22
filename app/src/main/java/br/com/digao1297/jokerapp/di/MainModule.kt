package br.com.digao1297.jokerapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.digao1297.jokerapp.data.ApiKeyRemoteDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {
    single<SharedPreferences> {
        getSharedPreferences(androidContext())
    }
    single<SharedPreferences.Editor> {
        getSharedPreferences(androidContext()).edit()
    }
    single<ApiKeyRemoteDataSource> {
        ApiKeyRemoteDataSource()
    }

}

private const val PREFS_FILE_KEY = "br.com.digao1297.jokeapp"

fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREFS_FILE_KEY, Context.MODE_PRIVATE)
}