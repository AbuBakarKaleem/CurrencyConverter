package com.app.currencyconverter.model.responsemodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrenciesModel(
   var success:String,
   var terms:String,
   var privacy:String,
   var currencies: HashMap<String,String>
): Parcelable
