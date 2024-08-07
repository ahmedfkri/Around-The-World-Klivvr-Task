package com.example.aroundtheworld.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aroundtheworld.data.model.City
import java.util.Locale

class Trie {

    private val root = TrieNode()

    @RequiresApi(Build.VERSION_CODES.N)
    fun insert(city: City) {
        var node = root
        for (char in city.name.lowercase(Locale.getDefault())) {
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        node.cities.add(city)
    }

    fun search(prefix: String): List<City> {
        var node = root
        for (char in prefix.lowercase(Locale.ROOT)) {
            node = node.children[char] ?: return emptyList()
        }
        return node.collectCities()
    }

    private fun TrieNode.collectCities(): List<City> {
        val result = mutableListOf<City>()
        result.addAll(cities)
        for (child in children.values) {
            result.addAll(child.collectCities())
        }
        return result
    }

    private class TrieNode {
        val children = mutableMapOf<Char, TrieNode>()
        val cities = mutableListOf<City>()
    }
}
