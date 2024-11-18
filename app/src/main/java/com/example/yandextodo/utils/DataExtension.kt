package com.example.yandextodo.utils

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@SuppressLint("NewApi")
fun Date?.toLocalDate(): LocalDate? {
    return this?.let {
        this.toInstant()
            .atZone(ZoneId.of("UTC"))
            .toLocalDate()
    }
}

@SuppressLint("NewApi")
fun LocalDate.toDate(): Date {
    return Date.from(
        this.atStartOfDay(ZoneId.of("UTC"))
            .toInstant()
    )
}