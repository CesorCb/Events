package com.cesor.android.eventsprueba3.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.cesor.android.eventsprueba3.R
import java.util.*

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.ui
 * Created by: César Castro on 20/10/2022 at 19:28.
 ***/
class DatePickerFragment(private val listener: (day: Int, month: Int, year: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val picker =
            DatePickerDialog(activity as Context, R.style.datePickerTheme, this, year, month, day)
        picker.datePicker.minDate = c.timeInMillis
        return picker
    }


    //OnDateSetListener
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }
}