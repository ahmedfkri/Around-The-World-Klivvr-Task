package com.example.aroundtheworld.ui.view_model

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aroundtheworld.data.model.City
import com.example.aroundtheworld.util.FileUtils
import com.example.aroundtheworld.util.Trie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

class CityViewModel : ViewModel() {

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> get() = _cities

    private val trie = Trie()

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadCities(context: Context) {
        val gson = Gson()
        val cityType = object : TypeToken<List<City>>() {}.type
        val cityList: List<City> = gson.fromJson(FileUtils.loadJSONFromAsset(context, "cities.json"), cityType)
        cityList.forEach { trie.insert(it) }
        _cities.value = cityList.sortedBy { it.name }
    }

    fun filterCities(prefix: String) {
        val filteredCities = trie.search(prefix).sortedBy { it.name }
        _cities.value = filteredCities
    }

}