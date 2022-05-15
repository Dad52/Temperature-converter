package com.ands.temperatureconverter

import android.app.Application

/**
 * Created by Dad52(Sobolev) on 5/15/2022.
 */
enum class Temperatures(val str: String) {
    FAHRENHEIT(getStr(R.string.FahrenheitItem)),
    CELSIUS(getStr(R.string.CelsiusItem)),
    KELVIN(getStr(R.string.KelvinItem)),
    RANKINE(getStr(R.string.RankineItem)),
    REAUMUR(getStr(R.string.ReaumurItem));
}

fun getStr(index: Int) : String {
    return Application().getString(index)
}