package com.app.currencyconverter.data.usecase

import com.app.currencyconverter.data.DataState
import com.app.currencyconverter.data.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCurrenciesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<DataState<List<String>>> {
        var currenciesFinalList = ArrayList<String>()
        repository.getAllCurrencies().collect {
            when (it) {
                is DataState.Success -> {
                    currenciesFinalList = getCurrenciesKeys(it.data.currencies)
                }
                is DataState.Error -> {
                    currenciesFinalList.add("error" + it.message)
                }

            }
        }
        return flow {
            emit(DataState.success(currenciesFinalList as List<String>))
        }
    }

    private fun getCurrenciesKeys(currenciesMap: HashMap<String, String>): ArrayList<String> {
        val currenciesList = ArrayList<String>()
        try {
            for (key in currenciesMap.keys) {
                currenciesList.add(currenciesMap[key] + "-" + key)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return currenciesList

    }
}