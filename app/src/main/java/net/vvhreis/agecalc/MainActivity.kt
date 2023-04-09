package net.vvhreis.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var txtSelectdText: TextView? = null
    private var txtAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        txtSelectdText = findViewById(R.id.txtSelectedDate)
        txtAgeInMinutes = findViewById(R.id.txtAgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(this,
    {view, year, month, dayOfMonth ->

        val selectedDate = "$dayOfMonth/${month+1}/$year"
        txtSelectdText?.text = selectedDate
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = sdf.parse(selectedDate)
        val selectedDateInMinutes = theDate.time / 60000
        val currenteDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMinuts = currenteDate.time/60000
        val diffInMinuts = currentDateInMinuts - selectedDateInMinutes

        txtAgeInMinutes?.text = diffInMinuts.toString()

    },year, month, day)
    dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
    dpd.show()
    }
}