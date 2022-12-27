package com.jetstocks.database

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
  @TypeConverter
  fun fromDateString(value: String?): LocalDate? {
    return value?.let { LocalDate.parse(it) }
  }

  @TypeConverter
  fun dateToDateString(date: LocalDate?): String? {
    return date?.toString()
  }
}