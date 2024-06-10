package com.example.kvalitaovzdusi.models

import com.google.gson.annotations.SerializedName


data class CityFeed (

  @SerializedName("status" ) var status : String? = null,
  @SerializedName("data"   ) var data   : Data?   = Data()

)