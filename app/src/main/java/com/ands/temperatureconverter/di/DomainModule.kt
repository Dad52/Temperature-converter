package com.ands.temperatureconverter.di

import android.content.Context
import com.ands.temperatureconverter.usecases.ConvertTemperaturesUseCase
import com.ands.temperatureconverter.usecases.FormatTemperatureUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by Dad52(Sobolev) on 5/15/2022.
 */
@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideFormatTemperatureUseCase(@ApplicationContext context: Context) : FormatTemperatureUseCase {
        return FormatTemperatureUseCase(context = context)
    }

    @Provides
    fun provideConvertTemperaturesUseCase(@ApplicationContext context: Context) : ConvertTemperaturesUseCase {
        return ConvertTemperaturesUseCase(context = context)
    }
}