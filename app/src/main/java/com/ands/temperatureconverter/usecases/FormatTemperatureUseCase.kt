package com.ands.temperatureconverter.usecases

import android.content.Context
import com.ands.temperatureconverter.R

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

        val unitsString = String.format(ROUND_MEASURE, result)

        when(secondTemperature) {
            FAHRENHEIT -> return context.getString(R.string.FahrenheitSign, unitsString)
            CELSIUS -> return context.getString(R.string.CelsiusSign, unitsString)
            KELVIN -> return context.getString(R.string.KelvinSign, unitsString)
            RANKINE -> return context.getString(R.string.RankineSign, unitsString)
            REAUMUR -> return context.getString(R.string.ReaumurSign, unitsString)
        }
        return String.format(ROUND_MEASURE, result)
    }

    companion object {
        private const val ROUND_MEASURE = "%.3f"
    }

}