package com.ands.temperatureconverter


import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ands.temperatureconverter.databinding.ActivityMainBinding
import com.ands.temperatureconverter.helpers.SimpleTextWatcher
import com.ands.temperatureconverter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val simpleTextWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            callCalculation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val FAHRENHEIT = getString(R.string.FahrenheitItem)
        val CELSIUS = getString(R.string.CelsiusItem)
        val KELVIN = getString(R.string.KelvinItem)
        val RANKINE = getString(R.string.RankineItem)
        val REAUMUR = getString(R.string.ReaumurItem)

        fillSpinners()
        observeResult()

        binding.apply {

            editTextSecond.inputType = InputType.TYPE_NULL

            cardViewCtoF.setOnClickListener() { pasteText(CELSIUS, FAHRENHEIT) }
            cardViewCtoK.setOnClickListener() { pasteText(CELSIUS, KELVIN) }
            cardViewCtoR.setOnClickListener() { pasteText(CELSIUS, RANKINE) }
            cardViewFtoC.setOnClickListener() { pasteText(FAHRENHEIT, CELSIUS) }
            cardViewFtoK.setOnClickListener() { pasteText(FAHRENHEIT, KELVIN) }
            cardViewFtoR.setOnClickListener() { pasteText(FAHRENHEIT, RANKINE) }

            imageSwapBtn.setOnClickListener() {
                val temp = spinnerFirst.text
                pasteText(spinnerSecond.text.toString(), temp.toString())
            }

            spinnerFirst.addTextChangedListener(simpleTextWatcher)
            spinnerSecond.addTextChangedListener(simpleTextWatcher)
            editTextFirst.addTextChangedListener(simpleTextWatcher)

        }

        //добавить Ньютон, Рёмер, Дениль

    }


    private fun callCalculation() {
        val firstTemperature = binding.spinnerFirst.text.toString()
        val secondTemperature = binding.spinnerSecond.text.toString()
        val units = binding.editTextFirst.text.toString()
        mainViewModel.calculate(firstTemperature, secondTemperature, units)
    }

    private fun observeResult() {
        mainViewModel.currentUnitsString.observe(this) { result ->
            binding.editTextSecond.setText(result)
        }
    }

    private fun pasteText(str1: String, str2: String) {
        binding.spinnerFirst.setText(str1)
        binding.spinnerSecond.setText(str2)
        fillSpinners()
    }

    private fun fillSpinners() = with(binding) {
        val units = resources.getStringArray(R.array.units)
        val arrayAdapter = ArrayAdapter(this@MainActivity, R.layout.dropdown_item, units)
        spinnerFirst.setAdapter(arrayAdapter)
        spinnerSecond.setAdapter(arrayAdapter)
    }

    override fun onResume() {
        super.onResume()
        fillSpinners()
    }

}