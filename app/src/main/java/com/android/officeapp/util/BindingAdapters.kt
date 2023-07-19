package com.android.kotlinmvvmtodolist.util

import android.annotation.SuppressLint
import android.graphics.Color

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.data.util.DateToString
import com.google.android.material.textview.MaterialTextView
import java.text.DateFormat
import java.util.*


@SuppressLint("SetTextI18n")
@BindingAdapter("setPriority")
fun setPriority(view: TextView, priority: Int){
    when(priority){
        0 -> {
            view.text = "حكم"
            view.setTextColor(Color.RED)
        }
        1 -> {
            view.text = "مؤجلة"
            view.setTextColor(Color.BLUE)
        }

    }

}@SuppressLint("SetTextI18n")
@BindingAdapter("setStation")
fun setStation(view: TextView, nameOfStation: Int){
    when(nameOfStation){
        0 -> {
            view.text = "أول أسيوط"
            view.setTextColor(Color.BLACK)
        }
        1-> {
            view.text = "ثاني أسيوط"
            view.setTextColor(Color.BLACK)
        }
        2-> {
            view.text = "مركز أسيوط"
            view.setTextColor(Color.BLACK)
        }
        3-> {
            view.text = "ديروط"
            view.setTextColor(Color.BLACK)
        }
       4-> {
            view.text = "منفلوط"
            view.setTextColor(Color.BLACK)
        }
        5 -> {
            view.text = "قوصية"
            view.setTextColor(Color.BLACK)
        }
        6-> {
            view.text = "الفتح"
            view.setTextColor(Color.BLACK)
        }
        7 -> {
            view.text = "أبنوب"
            view.setTextColor(Color.BLACK)
        }
        8 -> {
            view.text = "أبوتيج"
            view.setTextColor(Color.BLACK)
        }
       9 -> {
            view.text = "صدفا"
            view.setTextColor(Color.BLACK)
        }
        10 -> {
            view.text = "الغنايم"
            view.setTextColor(Color.BLACK)
        }
        11 -> {
            view.text = "ساحل سليم"
            view.setTextColor(Color.BLACK)
        }
        12 -> {
            view.text = "البداري"
            view.setTextColor(Color.BLACK)
        }
        13 -> {
            view.text = "إقتصادي"
            view.setTextColor(Color.BLACK)
        }

    }

}


@SuppressLint("SetTextI18n")
@BindingAdapter("setTypeOfSation")
fun setTypeOfSation(view: TextView, typeOfStation: Int){
    when(typeOfStation){
        0 -> {
            view.text = "إقتصادي"
            view.setTextColor(Color.BLACK)
        }
        1 -> {
            view.text = "استئناف إقتصادي"
            view.setTextColor(Color.BLACK)
        }
       2 -> {
            view.text = "مدني جزئي"
            view.setTextColor(Color.BLACK)
        }
        3 -> {
            view.text = "مدني كلي"
            view.setTextColor(Color.BLACK)
        }
        4 -> {
            view.text = "تجاري"
            view.setTextColor(Color.BLACK)
        }
        5 -> {
            view.text = "مدني مستأنف"
            view.setTextColor(Color.BLACK)
        }
        6-> {
            view.text = "استئناف عالي"
            view.setTextColor(Color.BLACK)
        }
        7 -> {
            view.text = "قضاء إداري"
            view.setTextColor(Color.BLACK)
        }
        8 -> {
            view.text = "جنح"
            view.setTextColor(Color.BLACK)
        }
        9 -> {
            view.text = "جنح مسئنف"
            view.setTextColor(Color.BLACK)
        }

        10 -> {
            view.text = "لجنة"
            view.setTextColor(Color.BLACK)
        }
        11-> {
            view.text = "خبراء"
            view.setTextColor(Color.BLACK)
        }

    }

}
@SuppressLint("SetTextI18n")
@BindingAdapter("set_date")
fun setDate(dueDate: MaterialTextView, date : Date){
    dueDate.text = "تاريخ الجلسة : " + DateToString.convertDateToString(date)
}
@SuppressLint("SetTextI18n")
@BindingAdapter("set_date2")
fun setDate2(dueDate: MaterialTextView, date : Date){
    dueDate.text = "تاريخ الجلسة : " + DateToString.convertDateToString(date)
}

@BindingAdapter("setTimestamp")
fun setTimestamp(view: TextView, timestamp: Long){
    view.text = DateFormat.getInstance().format(timestamp)
}