package br.com.digao1297.jokerapp.model

import android.os.Parcelable
import java.io.Serializable

data class Category(
    val name:String,
    val bgColor: Long
): Serializable
