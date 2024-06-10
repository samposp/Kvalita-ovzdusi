package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class SearchResult (

  @SerializedName("status" ) var status : String?         = null,
  @SerializedName("data"   ) var data   : ArrayList<DataS> = arrayListOf()

)