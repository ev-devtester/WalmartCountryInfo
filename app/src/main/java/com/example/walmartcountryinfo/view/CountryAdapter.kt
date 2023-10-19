package com.example.walmartcountryinfo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartcountryinfo.databinding.CountryListItemBinding
import com.example.walmartcountryinfo.model.CountryResponse

class CountryAdapter(
    private val list: MutableList<CountryResponse> = mutableListOf()
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun setCountryList(newList: List<CountryResponse>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(private val binding: CountryListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CountryResponse) {
            binding.apply {
                countryCode.text = item.code
                countryName.text = item.name + ", " + item.region
                capitolName.text = item.capital

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
                return CountryViewHolder(
                    CountryListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}