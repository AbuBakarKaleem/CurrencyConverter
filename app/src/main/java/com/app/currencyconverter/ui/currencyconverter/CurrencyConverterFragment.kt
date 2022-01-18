package com.app.currencyconverter.ui.currencyconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.currencyconverter.R
import com.app.currencyconverter.adapters.CurrencyListAdapter
import com.app.currencyconverter.base.BaseFragment
import com.app.currencyconverter.databinding.CurrencyConverterFragmentBinding
import com.app.currencyconverter.model.CurrencyList
import com.app.currencyconverter.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterFragment : BaseFragment<CurrencyConverterFragmentBinding>() {

    private lateinit var currencyListAdapter: CurrencyListAdapter
    private var currencyListLayoutManager: RecyclerView.LayoutManager? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CurrencyConverterFragmentBinding
        get() = CurrencyConverterFragmentBinding::inflate

    private val viewModel: CurrencyConverterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCurrencies()
        setUpObservers()
        setUpRecyclerView()
        activateListener()
    }

    private fun activateListener() {
        bi.btnConvertCurrency.setOnClickListener {
            getSelectedCurrencies(currencyListAdapter.getCurrenciesList())
        }
    }

    private fun setUpObservers() {

        viewModel.allCurrenciesListLiveData.observe(viewLifecycleOwner) {
            setUpCurrencySpinnerList(it)
            createListForRecyclerView(it)
        }
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingState -> {
                    bi.progressbar.visibility = View.VISIBLE
                    bi.rcvCurrencyList.visibility = View.GONE
                }
                is UnloadingState -> {
                    bi.progressbar.visibility = View.GONE
                    bi.rcvCurrencyList.visibility = View.VISIBLE
                }
                is EmptyState -> {
                    requireContext().toast(getString(R.string.no_currency_found))
                    bi.progressbar.visibility = View.GONE
                    bi.rcvCurrencyList.visibility = View.GONE
                }
                /*is ContentState -> {

                }*/
                is ErrorState -> {
                    requireContext().toast(it.message)
                    bi.progressbar.visibility = View.GONE
                    bi.rcvCurrencyList.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun createListForRecyclerView(currenciesList: List<String>) {

        try {
            var allCurrencyList = ArrayList<CurrencyList>()
            for (currency in currenciesList) {
                allCurrencyList.add(
                    CurrencyList(
                        currencyName = currency.split("-")[0].trim(),
                        currencyCode = currency.split("-")[1].trim(),
                        isSelected = false
                    )
                )
            }
            if (allCurrencyList.size > 0) {
                currencyListAdapter.updateItems(allCurrencyList)
            } else {
                requireContext().toast(getString(R.string.no_currency_found))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun setUpRecyclerView() {

        context?.let {
            val gridLayoutManager = GridLayoutManager(it, 3)
            this.currencyListAdapter = CurrencyListAdapter()
            currencyListAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            this.currencyListLayoutManager = gridLayoutManager
            bi.rcvCurrencyList.layoutManager = currencyListLayoutManager
            bi.rcvCurrencyList.adapter = currencyListAdapter
        }
    }

    private fun setUpCurrencySpinnerList(currenciesList: List<String>) {

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currenciesList
        ).also {
            // it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bi.spCurrencies.adapter = it
        }
    }

    private fun getSelectedCurrencies(currenciesList: ArrayList<CurrencyList>) {

        val selectCurrenciesList = currenciesList.filter {
            it.isSelected
        }
        if (selectCurrenciesList.isNotEmpty()) {
            createSelectedCurrenciesString(selectCurrenciesList as ArrayList<CurrencyList>)
        } else {
            requireContext().toast("Please select currency for Conversion")
        }
    }

    private fun createSelectedCurrenciesString(currenciesList: ArrayList<CurrencyList>) {
        try {
            var selectedCurrencies = ""
            for (i in 0 until currenciesList.size) {
                selectedCurrencies += if (i == currenciesList.size - 1) {
                    currenciesList[i].currencyCode
                } else {
                    currenciesList[i].currencyCode + ","
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}