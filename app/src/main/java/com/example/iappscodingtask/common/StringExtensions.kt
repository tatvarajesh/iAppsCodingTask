package com.example.iappscodingtask.common

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toFormatDatePublished() = try {
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault()).parse(this)
} catch (e: Exception) {
    null
}