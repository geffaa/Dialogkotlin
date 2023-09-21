package com.example.dialogkotlin

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dialogkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding
    private val province = arrayOf(
        "Jawa Tengah",
        "Daerah Istimewa Yogyakarta",
        "Bali"
    )

    private lateinit var countries : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        countries = resources.getStringArray(com.example.dialogkotlin.R.array.countries)
        with(binding) {
            val adapterProvince = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_item, province)
            adapterProvince.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerProvince.adapter = adapterProvince

            val adapterCountries = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_item, countries)
            adapterCountries.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerCountries.adapter = adapterCountries

            spinnerCountries.onItemSelectedListener =
                object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val countries = countries[position]
                        Toast.makeText(this@MainActivity, countries, Toast.LENGTH_SHORT)
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val countries = countries[position]
                        Toast.makeText(this@MainActivity, countries,
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

//            datePicker.init(
//                datePicker.year,
//                datePicker.month,
//                datePicker.dayOfMonth){
//                _, year, monthOfYear, dayOfMonth ->
//                val selectedDate = "$dayOfMonth/${monthOfYear +1}/$year"
//                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
//                Log.d("SELECTED DATE", "$dayOfMonth/${monthOfYear +1}/$year")
//            }

//            timePicker.setOnTimeChangedListener{ view, hourOfDay, minute ->
//                var selectedTime = String.format("%02d:%02d", hourOfDay, minute)
//
//                Toast.makeText(this@MainActivity, selectedTime,
//                    Toast.LENGTH_SHORT).show()
//                Log.d("SELECTED TIME", selectedTime)
//            }

            btnShowCalendar.setOnClickListener{
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "dataPicker")
            }

            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "TimePicker")
            }

        }
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val selectedDate = "$dayOfMonth, $month, $year"
        Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = "$minute, $hourOfDay"
        Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
    }
}