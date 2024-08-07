package com.example.aroundtheworld.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aroundtheworld.adapters.CityAdapter
import com.example.aroundtheworld.data.model.City
import com.example.aroundtheworld.databinding.ActivityMainBinding
import com.example.aroundtheworld.ui.view_model.CityViewModel

class CitiesActivity : AppCompatActivity(), CityAdapter.OnItemClickListener {

    lateinit var binding: ActivityMainBinding
    private val viewModel: CityViewModel by viewModels()
    private lateinit var cityAdapter: CityAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadCities(this)

        initializeAdapter()
        viewModel.cities.observe(this) { cities ->
            cityAdapter.updateList(cities)
        }


        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterCities(s.toString())
            }
        })


    }

    private fun initializeAdapter() {
        cityAdapter = CityAdapter(emptyList(), this)
        binding.rvCities.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = cityAdapter
        }

    }

    override fun onItemClick(city: City) {
        val uri = Uri.parse("geo:${city.coord.lat},${city.coord.lon}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}