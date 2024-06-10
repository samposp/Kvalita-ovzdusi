package com.example.kvalitaovzdusi

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kvalitaovzdusi.apiHandler.HTTPHandler
import com.example.kvalitaovzdusi.models.SearchResult
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchCityActivity : AppCompatActivity() {
    private val token = "05ad3c3a505d72222b268da4f9ddb55d22dc754b";
    private val host = "api.waqi.info"
    private val httpHandler = HTTPHandler(host)
    private var adapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_city)
        // Verify the action and get the query.
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                Search(query)
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun Search(query: String){
        val dest = "/search/?keyword=" + query + "&token=" + token
        GlobalScope.launch {
            val response = httpHandler.makeCall(dest)
            val searchResult = Gson().fromJson(response, SearchResult::class.java)
            Log.w("search query", query)

            withContext(Dispatchers.Main) {
                // set up the RecyclerView
                val recyclerView = findViewById<RecyclerView>(R.id.search_recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(baseContext)
                adapter = SearchAdapter(baseContext, searchResult)
                recyclerView.adapter = adapter
            }
        }
    }
}