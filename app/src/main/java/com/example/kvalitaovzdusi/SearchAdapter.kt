package com.example.kvalitaovzdusi

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kvalitaovzdusi.models.SearchResult

class SearchAdapter internal constructor(
    private val context: Context,
    private val mData: SearchResult
) :
    RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = mInflater.inflate(R.layout.search_item, parent, false)
        return ItemViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = mData.data[position]
        val cityName = data.station?.name
        holder.textView.text = cityName
        holder.layout.setOnClickListener {
            val intent = Intent(context, CityFeedActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("city", data.station?.url)
            context.startActivity(intent)
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.data.size
    }


    // stores and recycles views as they are scrolled off screen
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.city_text)
        val layout: LinearLayout = view.findViewById(R.id.city_layout)
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String? {
        return mData.data[id].station?.name
    }
}