package com.app.currencyconverter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.currencyconverter.databinding.CurrencyListItemBinding
import com.app.currencyconverter.model.CurrencyList

class CurrencyListAdapter() :
    RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>() {

    private val listItems: ArrayList<CurrencyList> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyListAdapter.CurrencyListViewHolder {
        val binding = CurrencyListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrencyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount() = listItems.size

    fun updateItems(currencyList: List<CurrencyList>) {
        listItems.clear()
        listItems.addAll(currencyList)
        notifyDataSetChanged()
    }

    inner class CurrencyListViewHolder(private val itemBinding: CurrencyListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(currency: CurrencyList) {
            itemBinding.tvCurrencyName.text = currency.currencyName
            itemBinding.tvCurrencyCode.text = currency.currencyCode
        }
    }
}
