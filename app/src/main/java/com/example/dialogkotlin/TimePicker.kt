package com.example.dialogkotlin

import android.app.Dialog
import android.app.TimePickerDialog

import android.text.format.DateFormat.is24HourFormat
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePicker : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireActivity(),
            activity as TimePickerDialog.OnTimeSetListener,
            hourOfDay,
            minute,
            is24HourFormat(activity)
        )
    }
}