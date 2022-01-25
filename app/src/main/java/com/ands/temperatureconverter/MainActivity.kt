package com.ands.temperatureconverter


import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ands.temperatureconverter.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var FAHRENHEIT: String
    private lateinit var CELSIUS: String
    private lateinit var KELVIN: String
    private lateinit var RANGIN: String
    private lateinit var REAUMUR: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        FAHRENHEIT = getString(R.string.FahrenheitItem)
        CELSIUS = getString(R.string.CelsiusItem)
        KELVIN = getString(R.string.KelvinItem)
        RANGIN = getString(R.string.RankinItem)
        REAUMUR = getString(R.string.ReaumurItem)

        fillSpinners()

        binding.apply {

            editTextSecond.inputType = InputType.TYPE_NULL

            cardViewCtoF.setOnClickListener() {
                spinnerFirst.setText(CELSIUS)
                spinnerSecond.setText(FAHRENHEIT)
                fillSpinners()
            }
            cardViewCtoK.setOnClickListener() {
                spinnerFirst.setText(CELSIUS)
                spinnerSecond.setText(KELVIN)
                fillSpinners()
            }
            cardViewCtoR.setOnClickListener() {
                spinnerFirst.setText(CELSIUS)
                spinnerSecond.setText(RANGIN)
                fillSpinners()
            }
            cardViewFtoC.setOnClickListener() {
                spinnerFirst.setText(FAHRENHEIT)
                spinnerSecond.setText(CELSIUS)
                fillSpinners()
            }
            cardViewFtoK.setOnClickListener() {
                spinnerFirst.setText(FAHRENHEIT)
                spinnerSecond.setText(KELVIN)
                fillSpinners()
            }
            cardViewFtoR.setOnClickListener() {
                spinnerFirst.setText(FAHRENHEIT)
                spinnerSecond.setText(RANGIN)
                fillSpinners()
            }

            imageSwapBtn.setOnClickListener() {
                val temp = spinnerFirst.text
                spinnerFirst.text = spinnerSecond.text
                spinnerSecond.text = temp
                fillSpinners()
            }

            spinnerFirst.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    setTextToSecondEditText(converter())
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })

            spinnerSecond.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    setTextToSecondEditText(converter())
                    Log.e("TAG", getString(R.string.RankinItem))
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })

            editTextFirst.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    setTextToSecondEditText(converter())
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

        }

    //добавить Ньютон, Рёмер, Дениль

    }

    private fun fillSpinners() = with(binding) {
        val units = resources.getStringArray(R.array.units)
        val arrayAdapter = ArrayAdapter(this@MainActivity, R.layout.dropdown_item, units)
        spinnerFirst.setAdapter(arrayAdapter)
        spinnerSecond.setAdapter(arrayAdapter)
    }

    private fun setTextToSecondEditText(calculatedUnit: String) = with(binding) {
        editTextSecond.setText(calculatedUnit)
    }

    private fun converter(): String {

        val firstUnitText = binding.editTextFirst.text.toString()
        val array: MutableList<String> = mutableListOf("-", "--", "---", "----", ".", "..")


        val b: Boolean = Pattern.matches("([0-9-.]+)", firstUnitText)
        if (firstUnitText.isNotEmpty() && firstUnitText !in array && b) {

            val units = binding.editTextFirst.text.toString().toDouble()
            val firstSpinner = binding.spinnerFirst.text.toString()
            val secondSpinner = binding.spinnerSecond.text.toString()

            when (firstSpinner) {
                FAHRENHEIT -> {
                    if (secondSpinner == FAHRENHEIT) return makeResult(units)
                    if (secondSpinner == CELSIUS) return makeResult ((units - 32) / 1.8)
                    if (secondSpinner == KELVIN) return makeResult((units + 459.67) / 1.8)
                    if (secondSpinner == RANGIN) return makeResult(units + 459.67)
                    if (secondSpinner == REAUMUR) return makeResult((units-32) * 0.44444)
                }
                CELSIUS -> {
                    if (secondSpinner == CELSIUS) return makeResult(units)
                    if (secondSpinner == FAHRENHEIT) return makeResult(units * 1.8 + 32)
                    if (secondSpinner == KELVIN) return makeResult (units + 273.15)
                    if (secondSpinner == RANGIN) return makeResult((units + 273.15) * 9 / 5)
                    if (secondSpinner == REAUMUR) return makeResult(units * 0.8)
                }
                KELVIN -> {
                    if (secondSpinner == KELVIN) return makeResult(units)
                    if (secondSpinner == FAHRENHEIT) return makeResult(units * 1.8 - 459.67)
                    if (secondSpinner == CELSIUS) return makeResult(units - 273.15)
                    if (secondSpinner == RANGIN) return makeResult(units * 9 / 5)
                    if (secondSpinner == REAUMUR) return makeResult((units-273.15)*0.8)
                }
                RANGIN -> {
                    if (secondSpinner == RANGIN) return makeResult(units)
                    if (secondSpinner == FAHRENHEIT) return makeResult(units - 459.67)
                    if (secondSpinner == CELSIUS) return makeResult((units - 491.67) * 5 / 9)
                    if (secondSpinner == KELVIN) return makeResult(units * 5 / 9)
                    if (secondSpinner == REAUMUR) return makeResult((units-491.67)*0.44444)
                }
                REAUMUR -> {
                    if (secondSpinner == REAUMUR) return makeResult(units)
                    if (secondSpinner == FAHRENHEIT) return makeResult(units * 2.25 + 32)
                    if (secondSpinner == CELSIUS) return makeResult(units / 0.8)
                    if (secondSpinner == KELVIN) return makeResult(units / 0.8 + 273.15)
                    if (secondSpinner == RANGIN) return makeResult(units * 2.25 + 491.67)
                }

            }
        } else {
            binding.editTextSecond.setText("0")//можно после ответа ещё знак его ставить
            return "0"
        }
    return "0"
    }

    private fun makeResult(result: Double): String {
        when(binding.spinnerSecond.text.toString()) {
            FAHRENHEIT -> return String.format(ROUND_MEASURE, result) + FAHRENHEIT_DEGREES_TEXT
            CELSIUS -> return String.format(ROUND_MEASURE, result) + CELSIUS_DEGREES_TEXT
            KELVIN -> return String.format(ROUND_MEASURE, result) + KELVIN_DEGREES_TEXT
            RANGIN -> return String.format(ROUND_MEASURE, result) + RANGIN_DEGREES_TEXT
            REAUMUR -> return String.format(ROUND_MEASURE, result) + REAUMUR_DEGREES_TEXT
        }
        return String.format(ROUND_MEASURE, result)
    }

    companion object {

        const val ROUND_MEASURE = "%.3f"

        const val KELVIN_DEGREES_TEXT = "°K"
        const val FAHRENHEIT_DEGREES_TEXT = "°F"
        const val CELSIUS_DEGREES_TEXT = "°C"
        const val RANGIN_DEGREES_TEXT = "°R"
        const val REAUMUR_DEGREES_TEXT = "°Re"

    }

}