package com.ands.temperatureconverter.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ands.temperatureconverter.usecases.FormatTemperatureUseCase
import com.ands.temperatureconverter.R
import com.ands.temperatureconverter.usecases.ConvertTemperaturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Dad52(Sobolev) on 5/15/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val formatTemperatureUseCase: FormatTemperatureUseCase,
    private val convertTemperaturesUseCase: ConvertTemperaturesUseCase
): AndroidViewModel(application) {

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

        val parsedUnits = parseInputUnits(units)

        if (firstTemperature == secondTemperature) {
            makeResult(secondTemperature = secondTemperature, units = parsedUnits)
            return
        }

        val result: Double = convertTemperaturesUseCase.execute(
            firstTemperature = firstTemperature,
            secondTemperature = secondTemperature,
            units = parsedUnits
        )

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