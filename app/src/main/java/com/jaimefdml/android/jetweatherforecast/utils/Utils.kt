package com.jaimefdml.android.jetweatherforecast.utils

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(timeStamp: Int): String =
    SimpleDateFormat("E, d MMM ").format(Date(timeStamp.toLong() * 1000))