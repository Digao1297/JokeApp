package br.com.digao1297.jokerapp.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id")
    val id: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("value")
    val value: String
)
