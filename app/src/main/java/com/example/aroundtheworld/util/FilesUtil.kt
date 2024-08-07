package com.example.aroundtheworld.util

import android.content.Context
import java.io.IOException
import java.io.InputStream

object FileUtils {
    fun loadJSONFromAsset(context: Context, fileName: String): String {
        val json: String?
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}