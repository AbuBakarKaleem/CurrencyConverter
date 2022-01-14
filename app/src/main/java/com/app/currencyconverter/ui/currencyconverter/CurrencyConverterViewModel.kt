package com.app.currencyconverter.ui.currencyconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.currencyconverter.data.DataState
import com.app.currencyconverter.data.usecase.GetAllCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel  @Inject constructor(
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
): ViewModel() {

    private var _uiState = MutableLiveData<UiState>()
    var uiStateLiveData: LiveData<UiState> = _uiState

    private var allCurrenciesList = MutableLiveData<List<String>>()
    var allCurrenciesListLiveData: LiveData<List<String>> = allCurrenciesList

    public fun getAllCurrencies(){
        try {
            _uiState.postValue(LoadingState)
            viewModelScope.launch {
                getAllCurrenciesUseCase.invoke().collect {
                    when (it) {
                        is DataState.Success -> {
                            if(it.data.isNotEmpty()){
                                allCurrenciesList.postValue(it.data)
                                _uiState.postValue(UnloadingState)
                            }else{
                                _uiState.postValue(EmptyState)
                            }
                        }
                        is DataState.Error -> {
                            _uiState.postValue(ErrorState(it.message))
                        }

                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}