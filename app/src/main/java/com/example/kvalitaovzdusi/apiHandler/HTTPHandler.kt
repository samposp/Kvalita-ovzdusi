package com.example.kvalitaovzdusi.apiHandler

import android.util.Log
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

class HTTPHandler (private val host: String){
    private val tag = "HttpHandler"
    fun makeCall(reqDestination: String): String {
        val url = URL(
            "Https",
            host,
            reqDestination
        )
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            Log.v("request", "input")
            val reader = BufferedReader(inputStream.reader())
            val content = StringBuilder()
            try {
                var line = reader.readLine()
                while (line != null) {
                    content.append(line)
                    line = reader.readLine()
                }
            } finally {
                reader.close()
            }
            return content.toString()
        } catch (e: MalformedURLException) {
            Log.e(tag, "MalformedURLException: " + e.message);
        } catch (e: ProtocolException) {
            Log.e(tag, "ProtocolException: " + e.message);
        } catch (e: IOException) {
            Log.e(tag, "IOException: " + e.message);
        } catch (e: Exception) {
            Log.e(tag, "Exception: " + e.message);
        }finally {
            urlConnection.disconnect()
        }
        return ""
    }
}