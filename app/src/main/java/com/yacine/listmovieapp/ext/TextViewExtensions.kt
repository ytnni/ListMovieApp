package com.yacine.listmovieapp.ext

import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun TextView.textParseFromDate(dateString : String?){
    var parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
    text = SimpleDateFormat("dd MMM yyyy").format(parsedDate).toString()
}