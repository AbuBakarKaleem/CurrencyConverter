package com.app.currencyconverter.utils

import android.app.Application
import com.app.currencyconverter.R

class StringUtils(private val appContext: Application) {
    fun noNetworkErrorMessage() = appContext.getString(R.string.message_no_network_connected_str)
    fun somethingWentWrong() = appContext.getString(R.string.message_something_went_wrong_str)
}
