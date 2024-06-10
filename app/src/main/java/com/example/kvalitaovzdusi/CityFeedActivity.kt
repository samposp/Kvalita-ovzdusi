package com.example.kvalitaovzdusi

import android.app.SearchManager
import android.content.ComponentName
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kvalitaovzdusi.apiHandler.HTTPHandler
import com.example.kvalitaovzdusi.models.CityFeed
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CityFeedActivity : AppCompatActivity() {
    private val token = "05ad3c3a505d72222b268da4f9ddb55d22dc754b";
    private val host = "api.waqi.info"
    private val httpHandler = HTTPHandler(host)
    private var city: String? = null
    private val defaultCity = "czechrepublic/liberecky/liberec-rochlice"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        city = intent.getStringExtra("city")
        if (city == null){
            city = defaultCity
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dest = "/feed/" + city + "/?token=" + token

        GlobalScope.launch {
            val response = httpHandler.makeCall(dest)
            val cityFeed = Gson().fromJson(response, CityFeed::class.java)
            cityFeed.data?.city?.name?.let { Log.w("response city", it) }
            withContext(Dispatchers.Main) {
                SetValues(cityFeed)
            }
        }

    }
    private fun setValue(id: Int, text: String?, addText: String = ""){
        val textV: TextView = findViewById(id)
        if (text != null && text != "null"){
            textV.text = text + addText
        }
        else {
            textV.text = "-" + addText
        }

    }
    private fun SetValues(feed: CityFeed){
        setValue(R.id.city, feed.data?.city?.name)
        setValue(R.id.aqi, feed.data?.aqi)
        setValue(R.id.time, feed.data?.time?.s)
        setValue(R.id.h, feed.data?.iaqi?.h?.v.toString(), " %")
        setValue(R.id.no2, feed.data?.iaqi?.no2?.v.toString(), " μg/m2")
        setValue(R.id.o3, feed.data?.iaqi?.o3?.v.toString(), " μg/m2")
        setValue(R.id.p, feed.data?.iaqi?.p?.v.toString(), " hPa")
        setValue(R.id.pm10, feed.data?.iaqi?.pm10?.v.toString(), " μg/m2")
        setValue(R.id.pm25, feed.data?.iaqi?.pm25?.v.toString(), " μg/m2")
        setValue(R.id.so2, feed.data?.iaqi?.so2?.v.toString(), " ppm")
        setValue(R.id.r, feed.data?.iaqi?.r?.v.toString(), " %")
        setValue(R.id.t, feed.data?.iaqi?.t?.v.toString(), " °C")
        setValue(R.id.w, feed.data?.iaqi?.w?.v.toString(), " km/h")
        setValue(R.id.station, feed.data?.attributions?.get(0)?.name)

        val aqi = feed.data?.aqi?.toDoubleOrNull()
        val i: Int
        if ( aqi == null ){
            i = -1
        } else if( aqi > 300){
            i = 5
        } else if (aqi > 200) {
            i = 4
        } else if (aqi > 150) {
            i = 3
        } else if (aqi > 100){
            i = 2
        } else if (aqi > 50){
            i = 1
        } else if (aqi >= 0) {
            i = 0
        } else {
            i = -1
        }

        val shortAqi: TextView = findViewById(R.id.AQIText)
        val longAqi: TextView = findViewById(R.id.AQILong)
        if (i != -1){
            shortAqi.text = resources.getStringArray(R.array.hodnota)[i]
            shortAqi.setBackgroundColor(resources.getIntArray(R.array.barvy)[i])
            longAqi.text = resources.getStringArray(R.array.uroven_pece)[i]
        }
        else {
            shortAqi.setBackgroundColor(resources.getColor(R.color.white))
            shortAqi.text = ""
            longAqi.text = ""
        }
        if (i == 1 || i == 2){
            shortAqi.setTextColor(resources.getColor(R.color.black))
        } else {
            shortAqi.setTextColor(resources.getColor(R.color.white))
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun Refresh(view: View) {
        val dest = "/feed/" + city + "/?token=" + token

        GlobalScope.launch {
            val response = httpHandler.makeCall(dest)
            val cityFeed = Gson().fromJson(response, CityFeed::class.java)
            cityFeed.data?.city?.name?.let { Log.w("response city", it) }
            withContext(Dispatchers.Main) {
                SetValues(cityFeed)
            }
        }
    }

    fun Back(view: View){
        finish()
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.top_menu, menu)
//
//        val searchItem = menu.findItem(R.id.menu_search)
//        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
//        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
//        searchView.setSearchableInfo(
//            searchManager.getSearchableInfo(
//                ComponentName(this, SearchCityActivity::class.java)
//            )
//        )
//        return true
//    }
}