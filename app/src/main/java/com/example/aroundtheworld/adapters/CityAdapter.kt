package com.example.aroundtheworld.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundtheworld.data.model.City
import com.example.aroundtheworld.databinding.CityListItemBinding

class CityAdapter(
    private var cities: List<City>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    // Define the OnItemClickListener interface
    fun interface OnItemClickListener {
        fun onItemClick(city: City)
    }

    class CityViewHolder(
        private val binding: CityListItemBinding,
        private val itemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.txtCity.text = city.name
            binding.txtCounty.text = city.country

            binding.root.setOnClickListener {
                itemClickListener.onItemClick(city)
            }
        }
    }

    fun updateList(newCities: List<City>) {
        cities = newCities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CityViewHolder(binding, itemClickListener)
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }
}
