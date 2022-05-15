package com.ands.temperatureconverter.usecases

import android.content.Context
import com.ands.temperatureconverter.R
import com.ands.temperatureconverter.viewmodel.MainViewModel

/**
 * Created by Dad52(Sobolev) on 5/15/2022.
 */
class ConvertTemperaturesUseCase(private val context: Context) {

    private val FAHRENHEIT = context.getString(R.string.FahrenheitItem)
    private val CELSIUS = context.getString(R.string.CelsiusItem)
    private val KELVIN = context.getString(R.string.KelvinItem)
    private val RANKINE = context.getString(R.string.RankineItem)
    private val REAUMUR = context.getString(R.string.ReaumurItem)

    fun execute(firstTemperature: String, secondTemperature: String, units: Double): Double {
        return when (firstTemperature) {
            FAHRENHEIT -> calculateFahrenheit(secondTemperature, units)
            CELSIUS -> calculateCelsius(secondTemperature, units)
            KELVIN -> calculateKelvin(secondTemperature, units)
            RANKINE -> calculateRankine(secondTemperature, units)
            REAUMUR -> calculateReaumur(secondTemperature, units)
            else -> ZERO_DOUBLE
        }
    }

    private fun calculateFahrenheit(secondTemperature: String, parsedUnits: Double): Double {

        return when (secondTemperature) {
            FAHRENHEIT -> parsedUnits
            CELSIUS -> (parsedUnits - 32) / 1.8
            KELVIN -> ((parsedUnits + 459.67) / 1.8)
            RANKINE -> (parsedUnits + 459.67)
            REAUMUR -> ((parsedUnits-32) * 0.44444)
            else -> ZERO_DOUBLE
        }
    }

    private fun calculateCelsius(secondTemperature: String, parsedUnits: Double): Double {

        return when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits * 1.8 + 32)
            CELSIUS -> parsedUnits
            KELVIN -> (parsedUnits + 273.15)
            RANKINE -> ((parsedUnits + 273.15) * 9 / 5)
            REAUMUR -> (parsedUnits * 0.8)
            else -> ZERO_DOUBLE
        }
    }

    private fun calculateKelvin(secondTemperature: String, parsedUnits: Double): Double {

        return when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits * 1.8 - 459.67)
            CELSIUS -> (parsedUnits - 273.15)
            KELVIN -> parsedUnits
            RANKINE -> (parsedUnits * 9 / 5)
            REAUMUR -> ((parsedUnits - 273.15) * 0.8)
            else -> ZERO_DOUBLE
        }
    }

    private fun calculateRankine(secondTemperature: String, parsedUnits: Double): Double {

        return when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits - 459.67)
            CELSIUS -> ((parsedUnits - 491.67) * 5 / 9)
            KELVIN -> (parsedUnits * 5 / 9)
            RANKINE -> parsedUnits
            REAUMUR -> ((parsedUnits-491.67)*0.44444)
            else -> ZERO_DOUBLE
        }

    }
    private fun calculateReaumur(secondTemperature: String, parsedUnits: Double): Double {

        return when (secondTemperature) {
            FAHRENHEIT -> (parsedUnits * 2.25 + 32)
            CELSIUS -> (parsedUnits / 0.8)
            KELVIN -> (parsedUnits / 0.8 + 273.15)
            RANKINE -> (parsedUnits * 2.25 + 491.67)
            REAUMUR -> parsedUnits
            else -> ZERO_DOUBLE
        }

    }

    companion object {
        const val ZERO_DOUBLE = 0.0
    }

}