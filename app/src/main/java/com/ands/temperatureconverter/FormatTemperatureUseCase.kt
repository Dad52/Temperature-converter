package com.ands.temperatureconverter

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by Dad52(Sobolev) on 5/15/2022.
 */
class FormatTemperatureUseCase(private val context: Context) {

    val FAHRENHEIT = context.getString(R.string.FahrenheitItem)
    val CELSIUS = context.getString(R.string.CelsiusItem)
    val KELVIN = context.getString(R.string.KelvinItem)
    val RANKINE = context.getString(R.string.RankineItem)
    val REAUMUR = context.getString(R.string.ReaumurItem)

    fun addSign(result: Double, secondTemperature: String): String {
        when(secondTemperature) {
            FAHRENHEIT -> return String.format(ROUND_MEASURE, result) + FAHRENHEIT_DEGREES_TEXT
            CELSIUS -> return String.format(ROUND_MEASURE, result) + CELSIUS_DEGREES_TEXT
            KELVIN -> return String.format(ROUND_MEASURE, result) + KELVIN_DEGREES_TEXT
            RANKINE -> return String.format(ROUND_MEASURE, result) + RANGIN_DEGREES_TEXT
            REAUMUR -> return String.format(ROUND_MEASURE, result) + REAUMUR_DEGREES_TEXT
        }
        return String.format(ROUND_MEASURE, result)
    }

    companion object {

        private const val ROUND_MEASURE = "%.3f"

        private const val KELVIN_DEGREES_TEXT = " °K"
        private const val FAHRENHEIT_DEGREES_TEXT = " °F"
        private const val CELSIUS_DEGREES_TEXT = " °C"
        private const val RANGIN_DEGREES_TEXT = " °R"
        private const val REAUMUR_DEGREES_TEXT = " °Re"

    }

}