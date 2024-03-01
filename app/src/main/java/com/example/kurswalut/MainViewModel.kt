package com.example.kurswalut

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _currenciesState = mutableStateOf(CurrencyState())
    val currenciesState: State<CurrencyState> = _currenciesState

    init {
        fetchCurrencies()
    }

    fun refresh(){
        fetchCurrencies()
    }

    private fun fetchCurrencies(){
        viewModelScope.launch {
            try {
                val response:List<CurrencyTable> = currencyService.getCurrencies()
                    for(currencyTable in response){
                _currenciesState.value= _currenciesState.value.copy(
                    list=currencyTable.rates,
                    loading = false,
                    error = null
                )
            }
            }
            catch (e:Exception){
                println("${e.message}")
                _currenciesState.value= _currenciesState.value.copy(
                    loading = false,
                    error="Fetching error ${e.message}"
                )

            }
        }
    }


    data class CurrencyState(
        val loading: Boolean = true,
        val list: List<CurrencyModel> = emptyList(),
        val error: String? = null
    )
}