package com.app.currencyconverter.utils

import android.content.Context
import android.os.Message
import android.widget.Toast


class Utility {

    companion object{
        fun showMessage(context: Context,message:String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }
}