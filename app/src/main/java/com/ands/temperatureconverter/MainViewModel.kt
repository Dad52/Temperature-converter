package com.ands.temperatureconverter


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * Created by Dad52(Sobolev) on 5/15/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val formatTemperatureUseCase: FormatTemperatureUseCase
): AndroidViewModel(application) {

    val FAHRENHEIT = application.getString(R.string.FahrenheitItem)
    val CELSIUS = application.getString(R.string.CelsiusItem)
    val KELVIN = application.getString(R.string.KelvinItem)
    val RANKINE = application.getString(R.string.RankineItem)
    val REAUMUR = application.getString(R.string.ReaumurItem)

    private val _currentUnits = MutableLiveData<Double>()
    val currentUnits: LiveData<Double> = _currentUnits

    private val _currentUnitsString = MutableLiveData<String>()
    val currentUnitsString: LiveData<String> = _currentUnitsString

    private fun parseInputUnits(units: String): Double {
        return try {
            units.trim().toDouble()
        } catch (e: Exception) {
            ZERO_DOUBLE
        }
    }

    fun calculate(firstTemperature: String, secondTemperature: String, units: String) {

        if (units.isEmpty()) return

        when (firstTemperature) {
            FAHRENHEIT -> calculateFahrenheit(secondTemperature, units)
            CELSIUS -> calculateCelsius(secondTemperature, units)
            KELVIN -> calculateKelvin(secondTemperature, units)
            RANKINE -> calculateRankine(secondTemperature, units)
            REAUMUR -> calculateReaumur(secondTemperature, units)
            else -> throw RuntimeException("Unknown temperature")
        }
    }

    private fun calculateFahrenheit(secondTemperature: String, units: String) {

        val parsedUnits = parseInputUnits(units)

        val result: Double = when (secondTemperature) {
            FAHRENHEIT -> parsedUnits
            CELSIUS -> (parsedUnits - 32) / 1.8
            KELVIN -> ((parsedUnits + 459.67) / 1.8)
            RANKINE -> (parsedUnits + 459.67)
            REAUMUR -> ((parsedUnits-32) * 0.44444)
            else -> ZERO_DOUBLE
        }
        makeResult(secondTemperature = secondTemperature, units = result)
    }

    private fun calculateCelsius(secondTemperature: String, units: String) {

        val parsedUnits = parseInputUnits(units)

        val result: Double = when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits * 1.8 + 32)
            CELSIUS -> parsedUnits
            KELVIN -> (parsedUnits + 273.15)
            RANKINE -> ((parsedUnits + 273.15) * 9 / 5)
            REAUMUR -> (parsedUnits * 0.8)
            else -> ZERO_DOUBLE
        }
        makeResult(secondTemperature = secondTemperature, units = result)
    }

    private fun calculateKelvin(secondTemperature: String, units: String) {

        val parsedUnits = parseInputUnits(units)

        val result: Double = when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits * 1.8 - 459.67)
            CELSIUS -> (parsedUnits - 273.15)
            KELVIN -> parsedUnits
            RANKINE -> (parsedUnits * 9 / 5)
            REAUMUR -> ((parsedUnits - 273.15) * 0.8)
            else -> ZERO_DOUBLE
        }
        makeResult(secondTemperature = secondTemperature, units = result)
    }

    private fun calculateRankine(secondTemperature: String, units: String) {

        val parsedUnits = parseInputUnits(units)

        val result: Double = when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits - 459.67)
            CELSIUS -> ((parsedUnits - 491.67) * 5 / 9)
            KELVIN -> (parsedUnits * 5 / 9)
            RANKINE -> parsedUnits
            REAUMUR -> ((parsedUnits-491.67)*0.44444)
            else -> ZERO_DOUBLE
        }
        makeResult(secondTemperature = secondTemperature, units = result)
    }
    private fun calculateReaumur(secondTemperature: String, units: String) {

        val parsedUnits = parseInputUnits(units)

        val result: Double = when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits * 2.25 + 32)
            CELSIUS -> (parsedUnits / 0.8)
            KELVIN -> (parsedUnits / 0.8 + 273.15)
            RANKINE -> (parsedUnits * 2.25 + 491.67)
            REAUMUR -> parsedUnits
            else -> ZERO_DOUBLE
        }
        makeResult(secondTemperature = secondTemperature, units = result)
    }

    private fun makeResult(secondTemperature: String, units: Double) {
        val newValue = formatTemperatureUseCase.addSign(result = units, secondTemperature = secondTemperature)
        _currentUnitsString.value = newValue
    }

    companion object {
        const val ZERO_DOUBLE = 0.0
    }

}