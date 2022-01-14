package com.app.currencyconverter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyConverterApp  : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}